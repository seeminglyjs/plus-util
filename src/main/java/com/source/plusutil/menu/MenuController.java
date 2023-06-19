package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

}
