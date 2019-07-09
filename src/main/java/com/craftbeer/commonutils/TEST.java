package com.craftbeer.commonutils;

import com.craftbeer.common.TokenUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.tools.jstat.Token;

public class TEST {
    public static void main(String[] args){
        String encoded= TokenUtils.generatePublicId("thongnb1");
        System.out.print(encoded);
    }
}
