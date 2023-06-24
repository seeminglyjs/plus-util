package com.source.plusutil.menu.repository;

import com.source.plusutil.menu.dto.NavDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NavRepository extends JpaRepository<NavDto, Long> {
}
