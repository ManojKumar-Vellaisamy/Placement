package com.rest.service;

import com.rest.models.MenuItem;
import com.rest.repository.MenuRepository;
import java.util.List;

public class MenuService {

    private final MenuRepository repository = new MenuRepository();

    public List<MenuItem> getAll() {
        return repository.findAll();
    }

    public MenuItem getById(int id) {
        return repository.findById(id);
    }

    public MenuItem addItem(String name, double price) {
        if (name.isEmpty() || price <= 0) {
            throw new IllegalArgumentException("Name required and price must be > 0");
        }
        return repository.save(name, price);
    }

    public boolean removeItem(int id) {
        return repository.deleteById(id);
    }
}
