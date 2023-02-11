package com.source.plusutil;

import com.source.plusutil.enums.urlPattern.UrlPatternEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.source.plusutil.enums.urlPattern.UrlPatternEnum.getNames;

@SpringBootTest
class PlusUtilApplicationTests {

//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	public void BCryptPasswordEncoderTest() {
//		String test = "test123";
//		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		System.out.println(passwordEncoder.encode(test));
//	}
	@Test
    public void test(){
        String [] aa =getNames(UrlPatternEnum.class);
        for(String s : aa){
            System.out.println(s);
        }
    }
}
