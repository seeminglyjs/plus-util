package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/nav/list")
    @ResponseBody
    public List<NavDto> getNavList(){
        return menuService.getNavList();
    }
    @GetMapping("/head/list")
    @ResponseBody
    public List<HeadDto> getHeadList(){
        return menuService.getHeadList();
    }
    @GetMapping("/menu/list")
    @ResponseBody
    public List<MenuDto> getMenuList(){
        return menuService.getMenuList();
    }
    @GetMapping("/menu/all/list")
    @ResponseBody
    public List<MenuResponseDto> getAllMenuList() {
        return menuService.getAllMenuList();
    }
    @GetMapping("/menu/join/list")
    @ResponseBody
    public List<NavInfoDto> getJoinMenuList(){return menuService.getJoinMenuList();}
    @PostMapping("/menu/enroll")
    @ResponseBody
    public MenuEnrollResponseDto enrollMenuInfo(@RequestBody MenuEnrollRequestDto menuEnrollRequestDto){
        return menuService.enrollMenuInfo(menuEnrollRequestDto);
    }

}
