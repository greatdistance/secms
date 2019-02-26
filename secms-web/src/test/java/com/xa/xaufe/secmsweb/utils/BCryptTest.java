package com.xa.xaufe.secmsweb.utils;

import com.xa.xaufe.secmsweb.BaseSpringTest;
import org.junit.Test;


public class BCryptTest extends BaseSpringTest {

    @Test
    public void testBcrypt(){
        //测试一个对明文密码进行Bcrypt加密。
        String password = "123456";
        String newpass = BCrypt.hashpw(password,BCrypt.gensalt(10));
        System.out.println(newpass);

        if(BCrypt.checkpw("1605990418","$2a$10$KyHNPiXzDXiwv5.W//1Ebu8EL.1JtqY6Imz.4OBxNx/C0gZ5IgdhS)
            System.out.println("密码一致");
        }else{
            System.out.println("密码不一致");
        }


    }
}
