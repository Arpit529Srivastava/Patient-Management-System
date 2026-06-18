package com.service;

import com.entity.MenuItem;
import com.repository.MenuDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    private final MenuDataStore menuDataStore;

    @Autowired
    public MenuServiceImpl(MenuDataStore menuDataStore) {
        this.menuDataStore = menuDataStore;
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuDataStore.findAll();
    }
}
