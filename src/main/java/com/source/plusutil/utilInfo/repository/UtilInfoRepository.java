package com.source.plusutil.utilInfo.repository;



import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilInfoRepository extends JpaRepository<UtilInfoDto, Long>{
    UtilInfoDto findByUtilName(String utilName);
    UtilInfoDto findByUrlPath(String urlPath);
}
