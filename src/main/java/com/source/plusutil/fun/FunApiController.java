package com.source.plusutil.fun;

import com.source.plusutil.enums.regex.RegexExpressionEnum;
import com.source.plusutil.enums.returnUrl.FunReturnUrl;
import com.source.plusutil.fun.FunApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/plus/fun/api")
@RequiredArgsConstructor
public class FunApiController {
    private final FunApiService funApiService;

    @GetMapping("/number/info/main")
    public String getFunNumberInfoMain(HttpServletRequest request){
        request.setAttribute("dateMonthSlashDayPermit", RegexExpressionEnum.DATE_MONTH_SLASH_DAY_PERMIT.getRegex());
        request.setAttribute("digit4Permit", RegexExpressionEnum.DIGIT_4_PERMIT.getRegex());
        request.setAttribute("digit8UnderPermit", RegexExpressionEnum.DIGIT_8_UNDER_PERMIT.getRegex());
        return FunReturnUrl.FUN_NUMBER_MAIN.getUrl();
    }

    @PostMapping("/number/info/action")
    public String getFunNumberInfoAction(HttpServletRequest request, String dataNumberInput, String dataNumberSelect){
        request.setAttribute("dateMonthSlashDayPermit", RegexExpressionEnum.DATE_MONTH_SLASH_DAY_PERMIT.getRegex());
        request.setAttribute("digit4Permit", RegexExpressionEnum.DIGIT_4_PERMIT.getRegex());
        request.setAttribute("digit8UnderPermit", RegexExpressionEnum.DIGIT_8_UNDER_PERMIT.getRegex());
        funApiService.numberInfoRequest(request, dataNumberInput, dataNumberSelect);
        return FunReturnUrl.FUN_NUMBER_MAIN.getUrl();
    }
}
