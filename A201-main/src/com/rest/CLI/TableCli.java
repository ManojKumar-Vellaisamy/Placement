package com.rest.CLI;

import com.rest.models.Table;
import com.rest.service.TableServices;

public class TableCli {
    private final TableServices service;

    public TableCli(TableServices service) {
        this.service = service;
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n=== TABLE MANAGEMENT ===");
            System.out.println("1. View All Tables");
            System.out.println("2. Free a Table manually");
            System.out.println("0. Back");
            choice = Input.integer("Enter choice: ");

            switch (choice) {
                case 1:
                    for (Table table : service.getAll()) {
                        System.out.println(table);
                    }
                    break;
                case 2:
                    int id = Input.integer("Table ID: ");
                    if (service.getById(id) == null) {
                        System.out.println("Table not found.");
                    } else {
                        service.free(id);
                        System.out.println("Table " + id + " is now free.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
