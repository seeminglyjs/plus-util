package com.source.plusutil.algorithm

import com.source.plusutil.enums.regex.RegexExpressionEnum
import com.source.plusutil.enums.returnUrl.SlidingWindowReturnUrl
import com.source.plusutil.algorithm.SlidingWindowServiceImpl
import com.source.plusutil.algorithm.dto.SlidingWindowRequestDto
import com.source.plusutil.algorithm.dto.SlidingWindowResponseDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/plus/algorithm")
class SlidingWindowController (private val slidingWindowService : SlidingWindowServiceImpl){

    @PostMapping("/sliding/window")
    @ResponseBody
    fun slidingWindowAction(@RequestBody slidingWindowRequestDto : SlidingWindowRequestDto): SlidingWindowResponseDto {
        return slidingWindowService.slidingWindowAction(slidingWindowRequestDto);
    }
}