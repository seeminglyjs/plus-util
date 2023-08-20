package com.source.plusutil.mypage.dto

import com.source.plusutil.mypage.enums.NickNameCheckStatus

class NickNameDuplicateCheckDto(
        val nickName : String,
        var checkStatus : NickNameCheckStatus = NickNameCheckStatus.BEFORE) {
    override fun toString(): String {
        return "NickNameDuplicateCheckDto(nickName='$nickName', checkStatus=$checkStatus)"
    }


}