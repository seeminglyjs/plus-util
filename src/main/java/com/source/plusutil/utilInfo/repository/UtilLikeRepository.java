package com.source.plusutil.utilInfo.repository;

import com.source.plusutil.utilInfo.dto.UtilLikesDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilLikeRepository  extends JpaRepository<UtilLikesDto, Long> {
    UtilLikesDto findByLikeIp(String likeIp);
    UtilLikesDto findByUtilNoAndLikeIp(long utilNo, String likeIp);
}
