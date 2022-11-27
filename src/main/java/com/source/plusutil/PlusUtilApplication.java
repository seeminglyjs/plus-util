package com.source.plusutil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //스프링 시큐리티 끄는 옵션
@SpringBootApplication
@ServletComponentScan // 해당 어노테이션이 있어야 @WebFilter 어노테이션이 동작한다. 서블릿컴포넌트(필터, 서블릿, 리스너)를 스캔해서 빈으로 등록한다.[필터: @WebFilter  서블릿: @WebServlet  리스너: @WebListener]
public class PlusUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlusUtilApplication.class, args);
	}

}
