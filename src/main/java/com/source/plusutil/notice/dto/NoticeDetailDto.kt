package com.source.plusutil.notice.dto

class NoticeDetailDto(val noticeDto: NoticeDto, val dateDto: DateDto, val updateRoleCheck : Boolean) {
    override fun toString(): String {
        return "NoticeDetailDto(noticeDto=$noticeDto, dateDto=$dateDto, updateRoleCheck=$updateRoleCheck)"
    }
}