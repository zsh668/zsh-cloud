package com.zsh.cloud.common.weixin.util.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zsh.cloud.common.weixin.domain.msg.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XML转换工具类.
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/17 14:31
 */
@Slf4j
public class XmlUtils {
    
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                boolean cdata = true;
                
                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
                
                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
    
    /**
     * xml转换成map
     *
     * @param xmlString
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @author hang
     * @date 2022/03/17 14:42
     */
    public static Map<String, String> parseXml(String xmlString) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new StringReader(xmlString));
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        } catch (DocumentException e) {
            log.error("xml解析异常", e);
        }
        return map;
    }
    
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
}
