package com.rest.repository;

import java.util.ArrayList;
import java.util.List;
import com.rest.models.Table;

public class TableRepository {
    private final List<Table> tables = new ArrayList<>();

    public TableRepository() {
        tables.add(new Table(1, 5));
        tables.add(new Table(2, 2));
        tables.add(new Table(3, 7));
    }

    public List<Table> show() {
        return tables;
    }

    public Table findById(int id) {
        for (Table table : tables) {
            if (table.getId() == id) {
                return table;
            }
        }
        return null;
    }
}
