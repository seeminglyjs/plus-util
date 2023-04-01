package com.source.plusutil.notice.dto

import org.springframework.data.domain.Page

class NoticeListDto (
        val pageExist:Boolean,
        val startPage:Int,
        val endPage:Int,
        val totalPage:Int,
        val noticePageList: Page<NoticeDto?>,
        val currentPage:Int,
        val noticeWriteRole:String
){
}