package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.MyPageRequestDto
import com.source.plusutil.mypage.dto.MyPageResponseDto
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/plus/my")
@RequiredArgsConstructor
class MyPageController (private val myPageService: MyPageService){

    @GetMapping("/page")
    @ResponseBody
    fun getMyPage(@RequestBody myPageRequestDto : MyPageRequestDto) : MyPageResponseDto? {
        return myPageService.getMyPage(myPageRequestDto)
    }
}