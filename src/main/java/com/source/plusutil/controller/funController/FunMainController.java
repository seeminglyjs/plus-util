package com.source.plusutil.controller.funController;

import com.source.plusutil.enums.returnUrl.FunReturnUrl;
import com.source.plusutil.service.funService.api.FunApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/plus/fun/")
@RequiredArgsConstructor
public class FunMainController {

    @GetMapping("/main")
    public String funMain(){
        return FunReturnUrl.FUN_MAIN.getUrl();
    }

}
