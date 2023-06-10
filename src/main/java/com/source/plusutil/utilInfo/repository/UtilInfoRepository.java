package com.source.plusutil.utilInfo.repository;



import com.source.plusutil.utilInfo.dto.UtilInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilInfoRepository extends JpaRepository<UtilInfoDto, Long>{
    UtilInfoDto findByUtilName(String utilName);
}
