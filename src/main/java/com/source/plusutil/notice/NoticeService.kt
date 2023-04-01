package com.source.plusutil.notice

import com.source.plusutil.notice.dto.*
import org.springframework.security.core.Authentication
import java.util.*

interface NoticeService {
    fun getNoticeList(authentication: Authentication?, currentPage: Int?): NoticeListDto?
    fun writeNotice(noticeWriteDto: NoticeWriteRequestDto, authentication: Authentication?) : NoticeWriteResponseDto
    fun makeNoticeWriteDto(userEmail: String?, noticeWriteRequestDto: NoticeWriteRequestDto?): NoticeWriteDto?
    fun getNoticeTotalPage(currentPage: Int?, listSize: Int?): Int?
    fun getNoticeDetailInfo(authentication: Authentication?, noticeNo: Long?): NoticeDetailDto?
    fun makeNoticeDtoWhenDetail(noticeDetailInfo: Optional<NoticeDto?>?, updateRoleCheck: Boolean): NoticeDetailDto?
    fun deleteNoticeInfo(noticeDeleteRequestDto: NoticeDeleteRequestDto) : NoticeDeleteResponseDto?
    fun updateNoticeInfo(noticeRequestDto: NoticeUpdateRequestDto) :NoticeUpdateResponseDto?
    fun makeNoticeDtoWhenUpdate(noticeDetailInfo: Optional<NoticeDto?>?, noticeRequestDto: NoticeUpdateRequestDto?): NoticeDto?
}