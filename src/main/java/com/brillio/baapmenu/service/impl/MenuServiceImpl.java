package com.brillio.baapmenu.service.impl;

import com.brillio.baapmenu.domain.Menu;
import com.brillio.baapmenu.exception.ServiceException;
import com.brillio.baapmenu.repository.MenuRepository;
import com.brillio.baapmenu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    private static final String SAVE_MSG = "Menu with ID %s saved";
    private static final String UPDATE_MSG = "Menu with ID %s update";
    private static final String ERR_MSG = "Failed to update: id cannot be null";
    private static final String INVALID_ID = "Failed to update: ID %s not found";

    private static Integer ID_SEQUENCE_START = 1001;
    private MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu save(Menu menu) {
        Optional<Menu> menuOptional = menuRepository.get().values().stream().toList().stream().sorted(Collections.reverseOrder()).findFirst();
        int id = 0;
        if (menuOptional.isPresent())
            id = menuOptional.get().getId() + 1;
        else id = ID_SEQUENCE_START;
        menu.setId(id);
        Menu menuObj =  menuRepository.save(menu);
        log.info(String.format(SAVE_MSG, menu.getId()));
        return menuObj;
    }

    @Override
    public Menu update(Menu menu) {
        validate(menu.getId());
        List<Menu> menuList = menuRepository.get().values().stream().toList();
        Menu menuObj = menuList.stream().filter(menu1 -> menu.getId().equals(menu1.getId())).findFirst().orElse(null);
        if (menuObj == null)
            throw new ServiceException(String.format(INVALID_ID, menu.getId()));
        menuObj.setDescription(menu.getDescription());
        menuObj.setName(menu.getName());
        menuObj.setPrice(menu.getPrice());
        menuRepository.update(menuObj);
        log.info(String.format(UPDATE_MSG, menu.getId()));
        return menuObj;
    }

    @Override
    public List<Menu> retrieve() {
        log.info("retrieve menu(s)");
       return menuRepository.get().values().stream().toList();
    }

    void validate(Integer id){
        if (id == null )
            throw new ServiceException(ERR_MSG);
    }

}
