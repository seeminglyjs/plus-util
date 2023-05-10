package com.source.plusutil.algorithm;

import com.source.plusutil.algorithm.dto.TwoPointerRequestDto;
import com.source.plusutil.algorithm.dto.TwoPointerResponseDto;
import com.source.plusutil.enums.regex.RegexExpressionEnum;
import com.source.plusutil.enums.returnUrl.AlgorithmReturnUrl;
import com.source.plusutil.algorithm.BasicServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/plus/algorithm")
@RequiredArgsConstructor
public class BasicAlgorithmController {
    private final BasicServiceImpl basicService;
    @PostMapping("/two/pointer/")
    @ResponseBody
    public TwoPointerResponseDto twoPointerAction(@RequestBody TwoPointerRequestDto twoPointerRequestDto){
        return basicService.twoPointerAction(twoPointerRequestDto);
    }
}
