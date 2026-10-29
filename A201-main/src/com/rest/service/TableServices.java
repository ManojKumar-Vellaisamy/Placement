package com.rest.service;

import java.util.List;

import com.rest.models.Table;
import com.rest.repository.TableRepository;

public class TableServices {
    private final TableRepository repository;

    public TableServices() {
        repository = new TableRepository();
    }

    public List<Table> getAll() {
        return repository.show();
    }

    public Table getById(int id) {
        return repository.findById(id);
    }

    public boolean isFree(int id) {
        Table table = repository.findById(id);
        return table != null && table.getStatus() == Table.Availability.free;
    }

    public void occupy(int id) {
        Table table = repository.findById(id);
        if (table != null) {
            table.booking();
        }
    }

    public void free(int id) {
        Table table = repository.findById(id);
        if (table != null) {
            table.freeNow();
        }
    }
}
