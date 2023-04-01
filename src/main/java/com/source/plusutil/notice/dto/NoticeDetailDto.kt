package com.source.plusutil.notice.dto

import com.source.plusutil.dto.etc.DateDto

class NoticeDetailDto(val noticeDto: NoticeDto, val dateDto: DateDto, val updateRoleCheck : Boolean) {
    override fun toString(): String {
        return "NoticeDetailDto(noticeDto=$noticeDto, dateDto=$dateDto, updateRoleCheck=$updateRoleCheck)"
    }
}