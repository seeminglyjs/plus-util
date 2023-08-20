package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.*

interface MyPageService {
    fun getMyPage(userNo: Int): MyPageDto?
    fun checkNickNameDuplicate(nickNameDuplicateCheckDto: NickNameDuplicateCheckDto): NickNameDuplicateCheckDto?
    fun modifyPage(myPageModifyRequestDto: MyPageModifyRequestDto): MyPageDto?
}