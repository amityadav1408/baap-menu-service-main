package com.brillio.baapmenu.repository;

import com.brillio.baapmenu.domain.Menu;
import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MenuRepository {

    private static final String MENU= "Menu";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Integer, Menu> hashOperations;

    public MenuRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public Menu save(Menu menu) {
        hashOperations.put(MENU, menu.getId(), menu);
        return menu;
    }


    public Menu update(Menu menu) {
        hashOperations.put(MENU, menu.getId(), menu);
        return menu;
    }

    public Menu getById(Integer id) {
        return hashOperations.get(MENU, id);
    }

    public Map<Integer, Menu> get() {
        return hashOperations.entries(MENU);
    }
}
