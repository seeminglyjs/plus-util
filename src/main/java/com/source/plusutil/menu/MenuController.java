package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.MenuEnrollRequestDto;
import com.source.plusutil.menu.dto.MenuEnrollResponseDto;
import com.source.plusutil.menu.dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menu/list")
    @ResponseBody
    public List<MenuResponseDto> getMenuList() {
        return menuService.getMenuList();
    }

    @PostMapping("/menu/enroll")
    @ResponseBody
    public MenuEnrollResponseDto enrollMenuInfo(@RequestBody MenuEnrollRequestDto menuEnrollRequestDto){
        return menuService.enrollMenuInfo(menuEnrollRequestDto);
    }

}