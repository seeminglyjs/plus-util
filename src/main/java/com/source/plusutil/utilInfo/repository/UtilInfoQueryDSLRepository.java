package com.source.plusutil.utilInfo.repository;

import com.source.plusutil.utilInfo.dto.UtilInfoDto;

import java.util.List;

public interface UtilInfoQueryDSLRepository {
    List<UtilInfoDto> getTopFiveUtilInfo();
}
