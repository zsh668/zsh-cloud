package com.zsh.cloud.system;

import com.zsh.cloud.common.core.util.ListUtils;
import com.zsh.cloud.system.application.model.dto.OrgTreeDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/15 13:50
 */
public class Test {
    
    public static void main(String[] args) {
        List<OrgTreeDTO> list = new ArrayList<>();
        OrgTreeDTO org = new OrgTreeDTO();
        org.setId("2");
        org.setOrgName("测试1");
        org.setParentId("1");
        list.add(org);
        org = new OrgTreeDTO();
        org.setId("3");
        org.setOrgName("测试2");
        org.setParentId("1");
        list.add(org);
        org = new OrgTreeDTO();
        org.setId("4");
        org.setOrgName("测试11");
        org.setParentId("2");
        list.add(org);
        org = new OrgTreeDTO();
        org.setId("1");
        org.setOrgName("测试11");
        org.setParentId("0");
        list.add(org);
    
        System.out.println(ListUtils.treeify(list));
        
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("zhangshuhang@naocs");
        System.out.println(pwd);
    }
    
}
