package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.MyPageRequestDto
import com.source.plusutil.mypage.dto.MyPageResponseDto

interface MyPageService {
    fun getMyPage(myPageRequestDto: MyPageRequestDto): MyPageResponseDto {
        TODO("Not yet implemented")
    }
}