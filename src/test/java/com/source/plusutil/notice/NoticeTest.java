package com.source.plusutil.notice;


import com.source.plusutil.dto.notice.NoticeWriteRequestDto;
import com.source.plusutil.service.noticeService.NoticeService;
import com.source.plusutil.service.noticeService.NoticeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class NoticeTest {

    @Autowired
    NoticeServiceImpl noticeService;

    @Test
    public void noticeWriteTest(){
        String randomTitle = "Title" + UUID.randomUUID().toString().split("-")[2];
        String randomContent = "Title" + UUID.randomUUID().toString().split("-")[2];

        NoticeWriteRequestDto noticeWriteRequestDto = new NoticeWriteRequestDto(randomTitle, randomContent, "notice",0);

//        noticeService.writeNotice(noticeWriteRequestDto);



    }

    @Test
    public void noticeDeleteByNoticeNo(){

    }

}
