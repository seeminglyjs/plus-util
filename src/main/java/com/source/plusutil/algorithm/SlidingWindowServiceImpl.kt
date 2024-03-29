package com.source.plusutil.algorithm

import com.source.plusutil.algorithm.dto.SlidingWindowRequestDto
import com.source.plusutil.algorithm.dto.SlidingWindowResponseDto
import org.springframework.stereotype.Service
import io.github.oshai.KotlinLogging

private val logger = KotlinLogging.logger {}

@Service
class SlidingWindowServiceImpl : SlidingWindowService {
    override fun slidingWindowAction(slidingWindowRequestDto: SlidingWindowRequestDto): SlidingWindowResponseDto {
        val arr: Array<String> = slidingWindowRequestDto.slidingWindowArr.toCharArray().map { it.toString() }.toTypedArray()
        val range: Int = slidingWindowRequestDto.slidingWindowRange.toInt(); //인트형으로 변환
        var maxValue = Int.MIN_VALUE;
        var sum = 0;
        var count = 0;
        var index = 0;
        for (numStr in arr) {
            count++;
            sum += numStr.toInt();
            if (count == range) {
                maxValue = sum.coerceAtLeast(maxValue); //Math.max
                count--;
                sum -= arr[index].toInt();
                index++;
            };
        }
        return SlidingWindowResponseDto(
                slidingWindowRequestDto.slidingWindowArr,
                slidingWindowRequestDto.slidingWindowRange,
                maxValue
        )
    }
}