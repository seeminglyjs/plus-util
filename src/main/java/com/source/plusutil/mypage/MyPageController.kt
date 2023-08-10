package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.MyPageDto
import com.source.plusutil.mypage.dto.MyPageRequestDto
import com.source.plusutil.mypage.dto.MyPageResponseDto
import com.source.plusutil.mypage.dto.NickNameDuplicateCheckDto
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

    /**
     * @param myPageRequestDto [마이페이지 정보 요청 객체]
     * @return MyPageDto [마이페이지 정보를 가진 entity]
     */
    @GetMapping("/page")
    @ResponseBody
    fun getMyPage(@RequestBody myPageRequestDto : MyPageRequestDto) : MyPageDto? {
        return myPageService.getMyPage(myPageRequestDto)
    }

    /**
     * @param nickNameDuplicateCheckDto [닉네임 중복체크 요청 객체]
     * @return NickNameDuplicateCheckDto [닉네임 중복체크 응답 객체]
     */
    @GetMapping("/check/nickname/duplicate")
    fun checkNickNameDuplicate(@RequestBody nickNameDuplicateCheckDto: NickNameDuplicateCheckDto) : NickNameDuplicateCheckDto? {
        println(nickNameDuplicateCheckDto.toString())
        return myPageService.checkNickNameDuplicate(nickNameDuplicateCheckDto)
    }
}