package com.source.plusutil.dto.notice

import com.source.plusutil.dto.etc.DateDto

class NoticeDetailDto(val noticeDto: NoticeDto, val dateDto: DateDto, val updateRoleCheck : Boolean) {
}