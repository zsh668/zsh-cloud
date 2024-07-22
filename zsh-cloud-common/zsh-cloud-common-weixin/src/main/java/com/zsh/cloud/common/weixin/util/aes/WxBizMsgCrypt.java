package com.zsh.cloud.common.weixin.util.aes;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

/**
 * 消息的加解密
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 17:12
 */
@Slf4j
public class WxBizMsgCrypt {
    
    private static final Base64 BASE64 = new Base64();
    
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    
    private static final ThreadLocal<DocumentBuilder> BUILDER_LOCAL = new ThreadLocal<DocumentBuilder>() {
        @Override
        protected DocumentBuilder initialValue() {
            try {
                final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setExpandEntityReferences(false);
                factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                return factory.newDocumentBuilder();
            } catch (ParserConfigurationException exc) {
                log.error("异常", exc);
                throw new IllegalArgumentException(exc);
            }
        }
    };
    
    protected byte[] aesKey;
    
    protected String token;
    
    protected String appId;
    
    /**
     * 构造函数.
     *
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appId          公众平台appid/corpid
     */
    public WxBizMsgCrypt(String token, String encodingAesKey, String appId) {
        this.token = token;
        this.appId = appId;
        this.aesKey = Base64.decodeBase64(encodingAesKey + "=");
    }
    
    /**
     * 生成xml消息
     *
     * @param encrypt   加密后的消息密文
     * @param signature 安全签名
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @return 生成的xml字符串
     */
    private String generateXml(String encrypt, String signature, String timestamp, String nonce) {
        
        String format =
                "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n" + "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n"
                        + "<TimeStamp>%3$s</TimeStamp>\n" + "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
        return String.format(format, encrypt, signature, timestamp, nonce);
        
    }
    
    /**
     * 提取出xml数据包中的加密消息
     *
     * @param xmltext 待提取的xml字符串
     * @return 提取出的加密消息字符串
     */
    public String extract(String xmltext) {
        try {
            DocumentBuilder db = BUILDER_LOCAL.get();
            Document document = db.parse(new InputSource(new StringReader(xmltext)));
            
            Element root = document.getDocumentElement();
            return root.getElementsByTagName("Encrypt").item(0).getTextContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // 生成4个字节的网络字节序
    private byte[] getNetworkBytesOrder(int sourceNumber) {
        byte[] orderBytes = new byte[4];
        orderBytes[3] = (byte) (sourceNumber & 0xFF);
        orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
        orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
        orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
        return orderBytes;
    }
    
    // 还原4个字节的网络字节序
    private int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }
    
    // 随机生成16位字符串
    private String getRandomStr() {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    /**
     * 将公众平台回复用户的消息加密打包.
     * <ol>
     * <li>对要发送的消息进行AES-CBC加密</li>
     * <li>生成安全签名</li>
     * <li>将消息密文和安全签名打包成xml格式</li>
     * </ol>
     *
     * @param replyMsg 公众平台待回复用户的消息，xml格式的字符串
     * @return 加密后的可以直接回复用户的密文，包括msg_signature, timestamp, nonce, encrypt的xml格式的字符串
     */
    public String encrypt(String replyMsg) {
        // 加密
        String encrypt = encrypt(getRandomStr(), replyMsg);
        
        // 生成安全签名
        String timeStamp = Long.toString(System.currentTimeMillis());
        String nonce = getRandomStr();
        
        String signature = SHA1.getSHA1(token, timeStamp, nonce, encrypt);
        
        // 生成发送的xml
        return generateXml(encrypt, signature, timeStamp, nonce);
    }
    
    /**
     * 对明文进行加密.
     *
     * @param text 需要加密的明文
     * @return 加密后base64编码的字符串
     */
    protected String encrypt(String randomStr, String text) {
        ByteGroup byteCollector = new ByteGroup();
        byte[] randomStrBytes = randomStr.getBytes(CHARSET);
        byte[] textBytes = text.getBytes(CHARSET);
        byte[] networkBytesOrder = getNetworkBytesOrder(textBytes.length);
        byte[] appidBytes = appId.getBytes(CHARSET);
        
        // randomStr + networkBytesOrder + text + appid
        byteCollector.addBytes(randomStrBytes);
        byteCollector.addBytes(networkBytesOrder);
        byteCollector.addBytes(textBytes);
        byteCollector.addBytes(appidBytes);
        
        // ... + pad: 使用自定义的填充方式对明文进行补位填充
        byte[] padBytes = PKCS7Encoder.encode(byteCollector.size());
        byteCollector.addBytes(padBytes);
        
        // 获得最终的字节流, 未加密
        byte[] unencrypted = byteCollector.toBytes();
        
        try {
            // 设置加密模式为AES的CBC模式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            
            // 加密
            byte[] encrypted = cipher.doFinal(unencrypted);
            
            // 使用BASE64对加密后的字符串进行编码
            return BASE64.encodeToString(encrypted);
        } catch (Exception e) {
            log.error("加密异常", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 对密文进行解密.
     *
     * @param text 需要解密的密文
     * @return 解密得到的明文
     */
    public String decrypt(String text) {
        byte[] original;
        try {
            // 设置解密模式为AES的CBC模式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            
            // 使用BASE64对密文进行解码
            byte[] encrypted = Base64.decodeBase64(text);
            
            // 解密
            original = cipher.doFinal(encrypted);
        } catch (Exception e) {
            log.error("解密异常", e);
            throw new RuntimeException(e);
        }
        
        String xmlContent;
        String fromAppid;
        try {
            // 去除补位字符
            byte[] bytes = PKCS7Encoder.decode(original);
            
            // 分离16位随机字符串,网络字节序和AppId
            byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);
            
            int xmlLength = recoverNetworkBytesOrder(networkOrder);
            
            xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
            fromAppid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length), CHARSET);
        } catch (Exception e) {
            log.error("获取xml数据异常", e);
            throw new RuntimeException(e);
        }
        
        // appid不相同的情况 暂时忽略这段判断
        if (!fromAppid.equals(appId)) {
            log.error("AppID不正确, fromAppid:{}, appId:{}", fromAppid, appId);
            //throw new RuntimeException("AppID不正确，请核实！");
        }
        return xmlContent;
    }
    
    /**
     * 检验消息的真实性，并且获取解密后的明文.
     * <ol>
     * <li>利用收到的密文生成安全签名，进行签名验证</li>
     * <li>若验证通过，则提取xml中的加密消息</li>
     * <li>对消息进行解密</li>
     * </ol>
     *
     * @param msgSignature 签名串，对应URL参数的msg_signature
     * @param timeStamp    时间戳，对应URL参数的timestamp
     * @param nonce        随机串，对应URL参数的nonce
     * @param postData     密文，对应POST请求的数据
     * @return 解密后的原文
     */
    public String decrypt(String msgSignature, String timeStamp, String nonce, String postData) {
        
        // 密钥，公众账号的app secret
        // 提取密文
        String encrypt = extract(postData);
        
        // 验证安全签名
        String signature = SHA1.getSHA1(token, timeStamp, nonce, encrypt);
        // 和URL中的签名比较是否相等
        if (!signature.equals(msgSignature)) {
            log.error("加密消息签名校验失败");
            throw new RuntimeException("加密消息签名校验失败");
        }
        
        // 解密
        return decrypt(encrypt);
    }
    
    /**
     * 检验消息的真实性，并且获取解密后的明文.
     * <ol>
     * <li>利用收到的密文生成安全签名，进行签名验证</li>
     * <li>若验证通过，则提取xml中的加密消息</li>
     * <li>对消息进行解密</li>
     * </ol>
     *
     * @param msgSignature 签名串，对应URL参数的msg_signature
     * @param timeStamp    时间戳，对应URL参数的timestamp
     * @param nonce        随机串，对应URL参数的nonce
     * @param postData     密文，对应POST请求的数据
     * @return 解密后的原文
     */
    public String decryptJson(String msgSignature, String timeStamp, String nonce, String postData) {
        
        // 密钥，公众账号的app secret
        // 验证安全签名
        String signature = SHA1.getSHA1(token, timeStamp, nonce, postData);
        // 和URL中的签名比较是否相等
        if (!signature.equals(msgSignature)) {
            log.error("加密消息签名校验失败");
            throw new RuntimeException("加密消息签名校验失败");
        }
        
        // 解密
        return decrypt(postData);
    }
}
