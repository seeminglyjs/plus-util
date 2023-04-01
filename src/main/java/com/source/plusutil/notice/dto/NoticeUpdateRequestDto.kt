package com.source.plusutil.notice.dto

class NoticeUpdateRequestDto(val noticeNo:Long, val noticeTitle:String, val noticeContent:String, val category:String) {
    override fun toString(): String {
        return "NoticeUpdateRequestDto(noticeNo=$noticeNo, noticeTitle='$noticeTitle', noticeContent='$noticeContent', category='$category')"
    }
}