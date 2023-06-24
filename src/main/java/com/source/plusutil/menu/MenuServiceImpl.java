package com.source.plusutil.menu;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.source.plusutil.menu.dto.*;
import com.source.plusutil.menu.enums.MenuTypeEnum;
import com.source.plusutil.menu.repository.HeadRepository;
import com.source.plusutil.menu.repository.MenuRepository;
import com.source.plusutil.menu.repository.NavRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final QueryDSLMenuRepository queryDSLMenuRepository;
    private final NavRepository navRepository;
    private final HeadRepository headRepository;
    private final MenuRepository menuRepository;

    @Override
    public List<NavDto> getNavList() {
        return queryDSLMenuRepository.findAllNavList();
    }

    @Override
    public List<MenuResponseDto> getMenuList() {
        List<NavInfoDto> navInfoDtoList = queryDSLMenuRepository.findAllMenuList();
        return null;
    }

    @Override
    public MenuEnrollResponseDto enrollMenuInfo(MenuEnrollRequestDto menuEnrollRequestDto) {
        ObjectMapper mapper = new ObjectMapper();
        if (menuEnrollRequestDto.getType().equals(MenuTypeEnum.NAV.getMenuType())) {
            NavDto navDto = null;
            try {// Object to NavDto
                navDto = mapper.readValue(mapper.writeValueAsString(menuEnrollRequestDto.getMenuObject()), NavDto.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            if(navDto == null) return MenuEnrollResponseDto.builder().success(false).build();
            else{
                return MenuEnrollResponseDto.builder()
                        .success(true)
                        .type(MenuTypeEnum.NAV.getMenuType())
                        .menuObject(navRepository.save(navDto))
                        .build();
            }
        } else if (menuEnrollRequestDto.getType().equals(MenuTypeEnum.HEAD.getMenuType())) {
            HeadDto headDto = null;
            try {// Object to HeadDto
                headDto = mapper.readValue(mapper.writeValueAsString(menuEnrollRequestDto.getMenuObject()), HeadDto.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            if(headDto == null) return MenuEnrollResponseDto.builder().success(false).build();
            else{
                return MenuEnrollResponseDto.builder()
                        .success(true)
                        .type(MenuTypeEnum.HEAD.getMenuType())
                        .menuObject(headRepository.save(headDto))
                        .build();
            }
        } else if (menuEnrollRequestDto.getType().equals(MenuTypeEnum.MENU.getMenuType())) {
            MenuDto MenuDto = null;
            try {// Object to MenuDto
                MenuDto = mapper.readValue(mapper.writeValueAsString(menuEnrollRequestDto.getMenuObject()), MenuDto.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            if(MenuDto == null) return MenuEnrollResponseDto.builder().success(false).build();
            else{
                return MenuEnrollResponseDto.builder()
                        .success(true)
                        .type(MenuTypeEnum.MENU.getMenuType())
                        .menuObject(menuRepository.save(MenuDto))
                        .build();
            }
        } else {
            log.info("[enrollMenuInfo TYPE ERROR]");
            return MenuEnrollResponseDto.builder().success(false).build();
        }
    }
}
