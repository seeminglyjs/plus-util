package com.source.plusutil.utilInfo.repository;

import com.source.plusutil.utilInfo.dto.entity.UtilViewsDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilViewRepository extends JpaRepository<UtilViewsDto, Long> {
    UtilViewsDto findByUtilNoAndViewIpAndViewDate(long utilNo, String viewIp, String viewDate);

}
