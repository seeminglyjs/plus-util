package com.source.plusutil.algorithm;

import com.source.plusutil.service.algorithmService.DfsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
public class DfsTest {

    @Autowired
    DfsServiceImpl dfsService;

    @Test
    public void dfsTest() {
        HttpServletRequest request = null;
        int result = 0;
        dfsService.dfsDefault(10, 7, 4, 1, 10, 7, null);
    }
}
