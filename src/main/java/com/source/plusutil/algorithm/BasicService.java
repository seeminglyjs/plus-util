package com.source.plusutil.algorithm;

import com.source.plusutil.algorithm.dto.TwoPointerRequestDto;
import com.source.plusutil.algorithm.dto.TwoPointerResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface BasicService {
    TwoPointerResponseDto twoPointerAction(TwoPointerRequestDto twoPointerRequestDto);
}
