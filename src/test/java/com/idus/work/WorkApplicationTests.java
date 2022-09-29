package com.idus.work;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

//@SpringBootTest
class WorkApplicationTests {

    @Test
    void contextLoads() {
        String passwd = "ㅇㅇㅇㅇㅇㅇ";
        // 대소문자 구분 숫자 특수문자  조합 9 ~ 12 자리
        String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$";
        String korEngPattern = "^[a-zA-Z가-힣]*$";
        String sEngPattern = "^[a-z]*$";

        Boolean tt = Pattern.matches(pwPattern, passwd);
        System.out.println(tt);

        String name = "박정진ddddSSS";
        System.out.println(Pattern.matches(korEngPattern, name));

        String nickName = "dddd";
        System.out.println(Pattern.matches(sEngPattern, nickName));
    }

}
