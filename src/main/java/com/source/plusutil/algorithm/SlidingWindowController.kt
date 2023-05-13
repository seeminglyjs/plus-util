package com.source.plusutil.algorithm

import com.source.plusutil.enums.regex.RegexExpressionEnum
import com.source.plusutil.enums.returnUrl.SlidingWindowReturnUrl
import com.source.plusutil.algorithm.SlidingWindowServiceImpl
import com.source.plusutil.algorithm.dto.SlidingWindowRequestDto
import com.source.plusutil.algorithm.dto.SlidingWindowResponseDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/plus/algorithm")
class SlidingWindowController (private val slidingWindowService : SlidingWindowServiceImpl){

    @PostMapping("/sliding/window")
    @ResponseBody
    fun slidingWindowAction(@RequestBody slidingWindowRequestDto : SlidingWindowRequestDto): SlidingWindowResponseDto {
        return slidingWindowService.slidingWindowAction(slidingWindowRequestDto);
    }
}