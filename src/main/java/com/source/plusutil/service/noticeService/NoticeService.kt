package com.source.plusutil.service.noticeService

import com.source.plusutil.dto.notice.*
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest

interface NoticeService {
    fun getNoticeList(authentication: Authentication?, currentPage: Int?): NoticeListDto?
    fun writeNotice(noticeWriteDto: NoticeWriteRequestDto, authentication: Authentication?) : NoticeWriteResponseDto
    fun getNoticeTotalPage(currentPage: Int?, listSize: Int?): Int?
    fun getNoticeDetailInfo(authentication: Authentication?, noticeNo: Long?): NoticeDetailDto?
    fun deleteNoticeInfo(noticeNo: Long?, currentPage: Int?) : NoticeDeleteResponseDto?
    fun updateNoticeInfo(request: HttpServletRequest?, authentication: Authentication?, noticeNo: Long?, noticeTitle: String?, noticeContent: String?, category: String?)
}