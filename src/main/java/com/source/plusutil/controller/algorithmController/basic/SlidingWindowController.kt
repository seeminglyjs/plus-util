package com.source.plusutil.controller.algorithmController.basic

import com.source.plusutil.enums.regex.RegexExpressionEnum
import com.source.plusutil.enums.returnUrl.SlidingWindowReturnUrl
import com.source.plusutil.service.algorithmService.SlidingWindowServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/plus/algorithm")
class SlidingWindowController (private val slidingWindowService : SlidingWindowServiceImpl){

    @GetMapping("/sliding/window/main")
    fun slidingWindowMain(request: HttpServletRequest): String {
        request.setAttribute("digit100UnderPermit", RegexExpressionEnum.DIGIT_100_UNDER_PERMIT.regex)
        return SlidingWindowReturnUrl.SLIDING_WINDOW_MAIN.url;
    }

    @PostMapping("/sliding/window/action")
    fun slidingWindowAction(request: HttpServletRequest, slidingWindowArr: String, slidingWindowRange: String): String {
        request.setAttribute("digit100UnderPermit", RegexExpressionEnum.DIGIT_100_UNDER_PERMIT.regex)
        slidingWindowService.slidingWindowAction(request,slidingWindowArr,slidingWindowRange);
        return SlidingWindowReturnUrl.SLIDING_WINDOW_MAIN.url;
    }
}