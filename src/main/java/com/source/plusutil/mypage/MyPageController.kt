package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.*
import lombok.RequiredArgsConstructor
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/plus/my")
@RequiredArgsConstructor
class MyPageController (private val myPageService: MyPageService){

    /**
     * @param userNo 유저 아이디
     *
     */
    @GetMapping("/page")
    @ResponseBody
    fun getMyPage(@RequestParam userNo : Int, @RequestParam(defaultValue = "yes", required = false) viewPlus : String) : MyPageInfoDto? {
        return myPageService.getMyPage(userNo, viewPlus)
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

    @PostMapping("/modify/page")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") //user 또는 admin 만 접근 가능
    fun modifyPage(@RequestBody myPageModifyRequestDto: MyPageModifyDto) : MyPageInfoDto? {
        return myPageService.modifyPage(myPageModifyRequestDto)
    }

    @PutMapping("/like/plus")
    fun likePlus(@RequestParam userNo : Int) : MyPageInfoDto? {
        return myPageService.likePlus(userNo)
    }

}