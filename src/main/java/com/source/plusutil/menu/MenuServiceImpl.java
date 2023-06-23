package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.MenuEnrollRequestDto;
import com.source.plusutil.menu.dto.MenuEnrollResponseDto;
import com.source.plusutil.menu.dto.MenuResponseDto;
import com.source.plusutil.menu.dto.NavInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final QueryDSLMenuRepository queryDSLMenuRepository;

    @Override
    public List<MenuResponseDto> getMenuList() {
        List<NavInfoDto> navInfoDtoList = queryDSLMenuRepository.findAllMenuList();
        return null;
    }

    @Override
    public MenuEnrollResponseDto enrollMenuInfo(MenuEnrollRequestDto menuEnrollRequestDto) {
        log.info(menuEnrollRequestDto.toString());
        return null;
    }

}
