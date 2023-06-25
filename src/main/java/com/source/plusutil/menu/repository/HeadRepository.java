package com.source.plusutil.menu.repository;

import com.source.plusutil.menu.dto.HeadDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadRepository extends JpaRepository<HeadDto, Long> {
    HeadDto findByHeadName(String headName);
}
