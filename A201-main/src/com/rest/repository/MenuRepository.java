package com.rest.repository;

import com.rest.models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private final List<MenuItem> items = new ArrayList<>();
    private int nextId = 1;

    public MenuRepository() {
        save("Masala Dosa", 80);
        save("Paneer Butter Masala", 180);
        save("Veg Biryani", 150);
        save("Chicken Biryani", 220);
        save("Filter Coffee", 40);
        save("Gulab Jamun", 60);
    }

    public MenuItem save(String name, double price) {
        MenuItem item = new MenuItem(nextId++, name, price);
        items.add(item);
        return item;
    }

    public List<MenuItem> findAll() {
        return items;
    }

    public MenuItem findById(int id) {
        for (MenuItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        return items.removeIf(item -> item.getId() == id);
    }
}
