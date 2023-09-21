package com.brillio.baapmenu.controller;

import com.brillio.baapmenu.domain.Menu;
import com.brillio.baapmenu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@Tag(name = "Menu Service")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get menu(s)")
    @ApiResponse(responseCode = "200", description = "menu details retrieved successfully")
    @ApiResponse(responseCode = "500", description = "error in retrieving menu(s)")
    List<Menu> retrieveMenu(@RequestParam(required = false) String name, @RequestParam(required = false) String description) {
       return menuService.retrieve(name, description);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "add menu")
    @ApiResponse(responseCode = "201", description = "menu saved successfully")
    @ApiResponse(responseCode = "500", description = "error in saving menu")
    void addMenuItem(@RequestBody Menu menu) {
        menuService.save(menu);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "update menu")
    @ApiResponse(responseCode = "204", description = "menu updated successfully")
    @ApiResponse(responseCode = "500", description = "error in updating menu")
    void updateMenuItem(@RequestBody Menu menu) {
        menuService.update(menu);
    }
}
