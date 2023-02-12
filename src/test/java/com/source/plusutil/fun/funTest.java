package com.source.plusutil.fun;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.utils.http.HttpRequestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class funTest {

    @Autowired
    PropertiesConfig config;
    @Test
    public void FunTest(){
        HttpRequestUtil hr = new HttpRequestUtil();
        String result = hr.httpApiGetRequestReturnString("http://numbersapi.com/47");
        result = result.replaceAll(" ","");
        String url = "https://openapi.naver.com/v1/papago/n2mt?source=en&target=ko&text="+result;
        System.out.println(hr.naverPapagoPostRequest(url, config.getNaverPapagoId(), config.getNaverPapagoSecret()));
    }
}
