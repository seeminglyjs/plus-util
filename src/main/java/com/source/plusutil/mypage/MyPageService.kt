package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.MyPageDto
import com.source.plusutil.mypage.dto.MyPageRequestDto
import com.source.plusutil.mypage.dto.MyPageResponseDto

interface MyPageService {
    fun getMyPage(myPageRequestDto: MyPageRequestDto): MyPageDto?
}