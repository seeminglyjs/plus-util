package com.source.plusutil.notice.dto

class NoticeDeleteRequestDto(val noticeNo:Long, val currentPage:Int) {
    override fun toString(): String {
        return "NoticeDeleteRequestDto(noticeNo=$noticeNo, currentPage=$currentPage)"
    }
}