package com.source.plusutil.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource(name = "app", value = "classpath:config/encrypt.properties", encoding = "UTF-8")
	,@PropertySource(name = "app", value = "classpath:config/encoding.properties", encoding = "UTF-8")
	,@PropertySource(name = "app", value = "classpath:config/db.properties", encoding = "UTF-8")
	,@PropertySource(name = "app", value = "classpath:config/user.properties", encoding = "UTF-8")
})
@Getter
public class PropertiesConfig {
	//

	//aes256알고리즘
	@Value("${AES_256_ALG}")
	private String aes256Alg;

	@Value("${DATASOURCE_URL}")
	private String datasourceUrl;

	@Value("${DATASOURCE_USERNAME}")
	private String datasourceUsername;
	
	@Value("${DATASOURCE_PASSWORD}")
	private String datasourcePassword;
	
	//사용자 파라미터 데이터 체크 변수
	@Value("${NO_DATA}")
	private String noData;

}
