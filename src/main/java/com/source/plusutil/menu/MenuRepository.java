package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.MenuDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuDto, Long> {
}
