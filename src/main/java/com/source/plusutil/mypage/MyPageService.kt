package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.*

interface MyPageService {
    fun getMyPage(userNo: Int, viewPlus: String): MyPageInfoDto?
    fun checkNickNameDuplicate(nickNameDuplicateCheckDto: NickNameDuplicateCheckDto): NickNameDuplicateCheckDto?
    fun modifyPage(myPageModifyDto: MyPageModifyDto): MyPageInfoDto?
    fun likePlus(userNo: Int): MyPageInfoDto?
}