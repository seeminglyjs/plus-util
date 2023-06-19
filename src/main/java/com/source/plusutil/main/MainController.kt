package com.source.plusutil.main

import com.source.plusutil.utilEtc.MainService
import com.source.plusutil.utilEtc.TestService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequiredArgsConstructor
@RequestMapping("/plus")
class MainController (val mainService: MainService){
    @GetMapping("/home")
    @ResponseBody
    fun mainPage(request: HttpServletRequest?): MainDataDto {
        return mainService.callMainPageMethod(request!!)
    }
}   