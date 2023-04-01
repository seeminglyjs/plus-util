package com.source.plusutil.notice.dto

class NoticeUpdateResponseDto(val updateOk : Boolean = false, val noticeNo : Long) {
    override fun toString(): String {
        return "NoticeUpdateResponseDto(updateOk=$updateOk, noticeNo=$noticeNo)"
    }
}