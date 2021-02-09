package es.sgie.back.util;

import es.sgie.back.SgieApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SgieApplication.class)
public class PasswordTest {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void bcrytText() {
        String plainText = "password";
        System.out.println("ENCODE (" + plainText + "):" + this.bCryptPasswordEncoder.encode(plainText));
    }

}
