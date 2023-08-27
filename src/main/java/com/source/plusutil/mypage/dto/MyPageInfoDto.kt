package com.source.plusutil.mypage.dto

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor


/**
 * myPage 정보 전달용 DTO
 */
data class MyPageInfoDto(
        val userNo : Int = -1,
        val nickName : String? = "",
        val description : String? = "",
        val viewCnt: Long? = 0,
        val likeCnt: Long? = 0,
        val userName : String? = "",
        val userPhone : String? = "",
        val phoneShow: String? = "",
        val nameShow: String? = ""
)