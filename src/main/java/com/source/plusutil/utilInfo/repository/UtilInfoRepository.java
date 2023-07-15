package com.source.plusutil.utilInfo.repository;



import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilInfoRepository extends JpaRepository<UtilInfoDto, Long>{
    Optional<UtilInfoDto> findByUtilName(String utilName);
    Optional<UtilInfoDto> findByUrlPath(String urlPath);
}
