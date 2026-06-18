package com.repository;

import com.entity.MenuItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuDataStore {

    private final List<MenuItem> menuItems = new ArrayList<>();

    public MenuDataStore() {
        menuItems.add(new MenuItem(1, "Margherita Pizza", 12.99, "Main Course"));
        menuItems.add(new MenuItem(2, "Caesar Salad", 8.50, "Appetizer"));
        menuItems.add(new MenuItem(3, "Grilled Salmon", 18.75, "Main Course"));
        menuItems.add(new MenuItem(4, "Chocolate Lava Cake", 6.99, "Dessert"));
        menuItems.add(new MenuItem(5, "Fresh Lemonade", 3.50, "Beverage"));
    }

    public List<MenuItem> findAll() {
        return new ArrayList<>(menuItems);
    }
}
