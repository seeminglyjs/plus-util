package com.source.plusutil.algorithm;

import com.source.plusutil.algorithm.dto.TwoPointerRequestDto;
import com.source.plusutil.algorithm.dto.TwoPointerResponseDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BasicServiceImpl implements BasicService {

    @Override
    public TwoPointerResponseDto twoPointerAction(TwoPointerRequestDto twoPointerRequestDto) {
        int target = Integer.parseInt(twoPointerRequestDto.getTwoPointerTarget());
        String[] arr = twoPointerRequestDto.getTwoPointerArr().split("");
        //시작포인터 , 종료포인터, 카운트 변수, 합계 변수
        int start = 0, end = 0, count = 0, sum = 0;

        while (start < arr.length) {
            if (sum > target || end == arr.length) {
                sum -= Integer.parseInt(arr[start]);
                start += 1;
            } else { // sum <= target
                sum += Integer.parseInt(arr[end]);
                end += 1;
            }
            if (sum == target) count++;
        }
        return TwoPointerResponseDto.builder()
                .twoPointerTarget(twoPointerRequestDto.getTwoPointerTarget())
                .twoPointerArr(twoPointerRequestDto.getTwoPointerArr())
                .twoPointerResult(count)
                .build();
    }
}
