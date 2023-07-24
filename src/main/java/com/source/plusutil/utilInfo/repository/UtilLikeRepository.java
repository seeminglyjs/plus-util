package com.source.plusutil.utilInfo.repository;

import com.source.plusutil.utilInfo.dto.entity.UtilLikesDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilLikeRepository  extends JpaRepository<UtilLikesDto, Long> {
    Optional<UtilLikesDto> findByUtilNoAndLikeIp(long utilNo, String likeIp);
    void deleteByUtilNoAndLikeIp(long utilNo, String likeIp);

}
