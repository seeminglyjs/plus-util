package com.source.plusutil.util.time;

import com.source.plusutil.utilTime.TimeUtilService;
import com.source.plusutil.utilTime.dto.TimeCalculateRequestDto;
import com.source.plusutil.utilTime.dto.TimeCalculateResponseDto;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@SpringBootTest
public class TimeCalculateTest {

    @Autowired
    TimeUtilService timeUtilService;

    @Test
    public void timeCalculate() throws Exception {
        String startDate = "20230202";
        String endDate = "20230203";
        String result = "1 일";
        TimeCalculateResponseDto timeCalculateResponseDto = timeUtilService.calculateDate(TimeCalculateRequestDto.builder()
                .startDateStr(startDate)
                .endDateStr(endDate)
                .build());
        MatcherAssert.assertThat("==== timeCalculateResponseDto result not 1  !!!!",timeCalculateResponseDto.getCalculateDay(), is(result));
    }
}
