package com.source.plusutil.service.noticeService

import com.source.plusutil.dto.notice.NoticeDetailDto
import com.source.plusutil.dto.notice.NoticeListDto
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest

interface NoticeService {
    fun getNoticeList(authentication: Authentication?, currentPage: Int?): NoticeListDto?
    fun writeNotice(noticeTitle: String?, noticeContent: String?, category: String?, request: HttpServletRequest?, authentication: Authentication?)
    fun getNoticeTotalPage(currentPage: Int?, listSize: Int?): Int?
    fun getNoticeDetailInfo(authentication: Authentication?, noticeNo: Int?): NoticeDetailDto?
    fun deleteNoticeInfo(request: HttpServletRequest?, authentication: Authentication?, noticeNo: Int?)
    fun updateNoticeInfo(request: HttpServletRequest?, authentication: Authentication?, noticeNo: Int?, noticeTitle: String?, noticeContent: String?, category: String?)
}