package com.source.plusutil.controller.algorithmController.basic;

import com.source.plusutil.enums.regex.RegexExpressionEnum;
import com.source.plusutil.enums.returnUrl.AlgorithmReturnUrl;
import com.source.plusutil.service.algorithmService.BasicServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/plus/algorithm")
@RequiredArgsConstructor
public class BasicAlgorithmController {
    private final BasicServiceImpl basicService;

    @GetMapping("/two/pointer/main")
    public String twoPointerMain(HttpServletRequest request){
        request.setAttribute("digit100UnderPermit", RegexExpressionEnum.DIGIT_100_UNDER_PERMIT.getRegex());
        return AlgorithmReturnUrl.TWO_POINTER_MAIN.getUrl();
    }

    @PostMapping("/two/pointer/action")
    public String twoPointerAction(HttpServletRequest request, String twoPointerArr, String twoPointerTarget){
        request.setAttribute("digit100UnderPermit", RegexExpressionEnum.DIGIT_100_UNDER_PERMIT.getRegex());
        basicService.twoPointerAction(request,twoPointerArr,twoPointerTarget);
        return AlgorithmReturnUrl.TWO_POINTER_MAIN.getUrl();
    }
}
