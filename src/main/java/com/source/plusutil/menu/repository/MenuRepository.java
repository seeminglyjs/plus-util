package com.source.plusutil.menu.repository;

import com.source.plusutil.menu.dto.MenuDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuDto, Long> {
    MenuDto findByMenuNameOrUrl(String menuName, String url);
}
