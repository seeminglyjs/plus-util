package com.source.plusutil.properties;

import com.source.plusutil.config.PropertiesConfig;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@SpringBootTest
public class PropertiesConfigGetTest {

    @Autowired
    PropertiesConfig propertiesConfig;

    @Test
    public void getGithubTokenTest(){
        System.out.println("propertiesConfig.getGithubSecretToken() ->" + propertiesConfig.getGithubSecretToken());
        MatcherAssert.assertThat("getGithubSecretToken is null ", propertiesConfig.getGithubSecretToken(), is(not(nullValue())));
    }

}
