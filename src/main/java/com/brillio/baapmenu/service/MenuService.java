package com.brillio.baapmenu.service;

import com.brillio.baapmenu.domain.Menu;

import java.util.List;


public interface MenuService {

    Menu save(Menu item);
    Menu update(Menu item);
    List<Menu> retrieve();
}
