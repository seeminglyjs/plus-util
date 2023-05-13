package com.source.plusutil.algorithm

import com.source.plusutil.algorithm.dto.SlidingWindowRequestDto
import com.source.plusutil.algorithm.dto.SlidingWindowResponseDto

interface SlidingWindowService {
    fun slidingWindowAction(slidingWindowRequestDto: SlidingWindowRequestDto) : SlidingWindowResponseDto;
}