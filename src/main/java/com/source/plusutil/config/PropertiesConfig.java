package com.source.plusutil.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(name = "app", value = "classpath:config/encrypt.properties", encoding = "UTF-8")
        , @PropertySource(name = "app", value = "classpath:config/encoding.properties", encoding = "UTF-8")
        , @PropertySource(name = "app", value = "classpath:config/db.properties", encoding = "UTF-8")
        , @PropertySource(name = "app", value = "classpath:config/user.properties", encoding = "UTF-8")
        , @PropertySource(name = "app", value = "classpath:config/api.properties", encoding = "UTF-8")
        , @PropertySource(name = "app", value = "classpath:config/key.properties", encoding = "UTF-8")
})
public class PropertiesConfig {

    //aes256알고리즘
    @Value("${AES_256_ALG}")
    private String aes256Alg;

    //aes256알고리즘
    @Value("${AES_256_KEY}")
    private String aes256key;

    //aes256알고리즘
    @Value("${AES_256_IV}")
    private String aes256iv;

    @Value("${DATASOURCE_URL}")
    private String datasourceUrl;

    @Value("${DATASOURCE_USERNAME}")
    private String datasourceUsername;

    @Value("${DATASOURCE_PASSWORD}")
    private String datasourcePassword;

    //사용자 파라미터 데이터 체크 변수
    @Value("${NO_DATA}")
    private String noData;

    @Value("${NAVER.PAPAGO.ID}")
    private String naverPapagoId;

    @Value("${NAVER.PAPAGO.SECRET}")
    private String naverPapagoSecret;

    @Value("${GITHUB.SECRET.TOKEN}")
    private String githubSecretToken;

    public String getAes256Alg() {
        return aes256Alg;
    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public String getDatasourceUsername() {
        return datasourceUsername;
    }

    public String getDatasourcePassword() {
        return datasourcePassword;
    }

    public String getNoData() {
        return noData;
    }

    public String getNaverPapagoId() {
        return naverPapagoId;
    }

    public String getNaverPapagoSecret() {
        return naverPapagoSecret;
    }

    public String getGithubSecretToken() {
        return githubSecretToken;
    }

    public String getAes256key() {
        return aes256key;
    }

    public String getAes256iv() {
        return aes256iv;
    }
}
