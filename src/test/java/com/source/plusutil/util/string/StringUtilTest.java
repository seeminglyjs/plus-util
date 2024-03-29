package com.source.plusutil.util.string;

import com.source.plusutil.utilString.StringUtilService;
import com.source.plusutil.utilString.dto.StringInitialRequestDto;
import com.source.plusutil.utilString.dto.StringInitialResponseDto;
import com.source.plusutil.utilString.dto.StringReplaceRequestDto;
import com.source.plusutil.utilString.dto.StringReplaceResponseDto;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;


@SpringBootTest
public class StringUtilTest {

    @Autowired
    StringUtilService stringUtilService;

    @Test
    public void getInitialString() { //초성 추출 정상 여부 테스트
        StringInitialResponseDto stringInitialResponseDto = stringUtilService.getInitialString(StringInitialRequestDto.builder()
                .stringContent("안녕하세요.")
                .build());
        System.out.println("stringInitialResponseDto -> " + stringInitialResponseDto.toString());
        MatcherAssert.assertThat("stringInitialResponseDto is null", stringInitialResponseDto, is(not(nullValue())));
        MatcherAssert.assertThat("stringInitialResponseDto getInitialString not ㅇㄴㅎㅅㅇ.", stringInitialResponseDto.getInitialString(), is("ㅇㄴㅎㅅㅇ."));
    }

    @Test
    public void stringReplaceTest(){
        StringReplaceRequestDto stringReplaceRequestDto = new StringReplaceRequestDto("!", "?", "test!!!!!");
        StringReplaceResponseDto stringReplaceResponseDto = stringUtilService.stringReplace(stringReplaceRequestDto);
        MatcherAssert.assertThat("content is not [test?????]", stringReplaceResponseDto.getContent(), is("test?????"));
        stringReplaceResponseDto = stringUtilService.stringReplace(null);
        MatcherAssert.assertThat("content is not [값을 입력해주세요.]", stringReplaceResponseDto.getContent(), is("값을 입력해주세요."));
    }
}
