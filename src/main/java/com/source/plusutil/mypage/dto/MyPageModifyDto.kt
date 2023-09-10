package com.source.plusutil.mypage.dto

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import lombok.ToString

@AllArgsConstructor
@NoArgsConstructor
@ToString
class MyPageModifyDto (
        val userNo : Int,
        val nickName : String? = null,
        val description : String? = null,
        val userName : String? = null,
        val userPhone : String? = null,
        val phoneShow: String? = null,
        val nameShow: String? = null
)