package org.sang.controller;

import org.sang.bean.Hr;
import org.sang.bean.Menu;
import org.sang.common.HrUtils;
import org.sang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 这是一个只要登录就能访问的Controller
 * 主要用来获取一些配置信息
 *
 * @author sang
 * @date 2017/12/28
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    private final MenuService menuService;

    @Autowired
    public ConfigController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping("/sysmenu")
    public List<Menu> sysmenu() {
        return menuService.getMenusByHrId();
    }

    @RequestMapping("/hr")
    public Hr currentUser() {
        return HrUtils.getCurrentHr();
    }
}
