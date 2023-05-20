package com.source.plusutil.notice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.source.plusutil.notice.dto.NoticeDto;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeDto, Long> {



}
