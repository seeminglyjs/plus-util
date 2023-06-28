package com.source.plusutil.menu;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.source.plusutil.menu.dto.*;
import com.source.plusutil.menu.enums.MenuTypeEnum;
import com.source.plusutil.menu.repository.HeadRepository;
import com.source.plusutil.menu.repository.MenuRepository;
import com.source.plusutil.menu.repository.NavRepository;
import com.source.plusutil.menu.repository.QueryDSLMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {

    private final QueryDSLMenuRepository queryDSLMenuRepository;
    private final NavRepository navRepository;
    private final HeadRepository headRepository;
    private final MenuRepository menuRepository;

    @Override
    public List<NavDto> getNavList() {
        return queryDSLMenuRepository.findNavList();
    }

    @Override
    public List<HeadDto> getHeadList() {
        return queryDSLMenuRepository.findHeadList();
    }

    @Override
    public List<MenuDto> getMenuList() {
        return queryDSLMenuRepository.findMenuList();
    }

    @Override
    public List<MenuResponseDto> getAllMenuList() {
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
                log.info("===== [JsonProcessingException] ERROR =====");
                throw new RuntimeException(e);
            }
            if(navDto == null) return MenuEnrollResponseDto.builder().success(false).message("요청 정보를 확인해 주세요.").build();
            else{
                NavDto alredyNavDto = navRepository.findByNavName(navDto.getNavName()); //이미 존재하는 이름인지 확인
                if(alredyNavDto != null) return MenuEnrollResponseDto.builder().success(false).message("네비게이션 이름이 이미 존재합니다.").build();
                return MenuEnrollResponseDto.builder()
                        .success(true)
                        .message("네비게이션 등록해 성공했습니다.")
                        .type(MenuTypeEnum.NAV.getMenuType())
                        .menuObject(navRepository.save(navDto))
                        .build();
            }
        } else if (menuEnrollRequestDto.getType().equals(MenuTypeEnum.HEAD.getMenuType())) {
            HeadDto headDto = null;
            try {// Object to HeadDto
                headDto = mapper.readValue(mapper.writeValueAsString(menuEnrollRequestDto.getMenuObject()), HeadDto.class);
            } catch (JsonProcessingException e) {
                log.info("===== [JsonProcessingException] ERROR =====");
                throw new RuntimeException(e);
            }

            if(headDto == null) return MenuEnrollResponseDto.builder().success(false).message("요청 정보를 확인해 주세요.").build();
            else{
                HeadDto alreadyHeadDto = headRepository.findByHeadName(headDto.getHeadName()); //이미 존재하는 이름인지 확인
                if(alreadyHeadDto != null) return MenuEnrollResponseDto.builder().success(false).message("주 메뉴 이름이 이미 존재합니다.").build();
                return MenuEnrollResponseDto.builder()
                        .success(true)
                        .message("주 메뉴 등록에 성공했습니다.")
                        .type(MenuTypeEnum.HEAD.getMenuType())
                        .menuObject(headRepository.save(headDto))
                        .build();
            }
        } else if (menuEnrollRequestDto.getType().equals(MenuTypeEnum.MENU.getMenuType())) {
            MenuDto MenuDto = null;
            try {// Object to MenuDto
                MenuDto = mapper.readValue(mapper.writeValueAsString(menuEnrollRequestDto.getMenuObject()), MenuDto.class);
            } catch (JsonProcessingException e) {
                log.info("===== [JsonProcessingException] ERROR =====");
                throw new RuntimeException(e);
            }

            if(MenuDto == null) return MenuEnrollResponseDto.builder().success(false).message("요청 정보를 확인해 주세요.").build();
            else{
                MenuDto alreadyMenuDto = menuRepository.findByMenuNameOrUrl(MenuDto.getMenuName(), MenuDto.getUrl());
                if (alreadyMenuDto != null) return MenuEnrollResponseDto.builder().success(false).message("메뉴 이름 또는 경로 정보가 이미 존재합니다.").build();
                return MenuEnrollResponseDto.builder()
                        .success(true)
                        .message("메뉴 등록에 성공했습니다.")
                        .type(MenuTypeEnum.MENU.getMenuType())
                        .menuObject(menuRepository.save(MenuDto))
                        .build();
            }
        } else {
            log.info("[enrollMenuInfo TYPE ERROR]");
            return MenuEnrollResponseDto.builder().success(false).build();
        }
    }

    @Override
    public List<NavInfoDto> getJoinMenuList() {
        return queryDSLMenuRepository.findAllMenuList();
    }
}
