package com.source.plusutil.repository.noticeRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.source.plusutil.dto.notice.NoticeDto;

public interface NoticeRepository extends JpaRepository<NoticeDto, Integer> {

}
