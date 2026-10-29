package com.rest.CLI;

import com.rest.models.MenuItem;
import com.rest.service.MenuService;

public class MenuCli {
    private final MenuService service;

    public MenuCli(MenuService service) {
        this.service = service;
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n=== MENU MANAGEMENT ===");
            System.out.println("1. View Menu");
            System.out.println("2. Add Item");
            System.out.println("3. Remove Item");
            System.out.println("0. Back");
            choice = Input.integer("Enter choice: ");

            try {
                switch (choice) {
                    case 1:
                        show();
                        break;
                    case 2:
                        String name = Input.text("Item name: ");
                        double price = Input.decimal("Price: Rs. ");
                        MenuItem item = service.addItem(name, price);
                        System.out.println("Added: " + item);
                        break;
                    case 3:
                        int id = Input.integer("Item ID to remove: ");
                        System.out.println(service.removeItem(id) ? "Removed." : "Item not found.");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 0);
    }

    public void show() {
        System.out.println("--- MENU ---");
        for (MenuItem item : service.getAll()) {
            System.out.println(item);
        }
    }
}
