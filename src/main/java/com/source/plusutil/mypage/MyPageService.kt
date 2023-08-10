package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.MyPageDto
import com.source.plusutil.mypage.dto.MyPageRequestDto
import com.source.plusutil.mypage.dto.MyPageResponseDto
import com.source.plusutil.mypage.dto.NickNameDuplicateCheckDto

interface MyPageService {
    fun getMyPage(myPageRequestDto: MyPageRequestDto): MyPageDto?
    fun checkNickNameDuplicate(nickNameDuplicateCheckDto: NickNameDuplicateCheckDto): NickNameDuplicateCheckDto?
}