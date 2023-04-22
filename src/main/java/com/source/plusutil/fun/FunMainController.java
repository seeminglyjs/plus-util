package com.source.plusutil.fun;

import com.source.plusutil.enums.returnUrl.FunReturnUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plus/fun/")
@RequiredArgsConstructor
public class FunMainController {

    @GetMapping("/main")
    public String funMain(){
        return FunReturnUrl.FUN_MAIN.getUrl();
    }

}
