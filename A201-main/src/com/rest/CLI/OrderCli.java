package com.rest.CLI;

import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.models.Table;
import com.rest.service.MenuService;
import com.rest.service.OrderService;
import com.rest.service.TableServices;

public class OrderCli {
    private final OrderService orders;
    private final TableServices tables;
    private final MenuCli menuCli;

    public OrderCli(OrderService orders, TableServices tables, MenuService menu) {
        this.orders = orders;
        this.tables = tables;
        this.menuCli = new MenuCli(menu);
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n=== ORDER MANAGEMENT ===");
            System.out.println("1. New Order (assign table)");
            System.out.println("2. Add Item to Order");
            System.out.println("3. Remove Item from Order");
            System.out.println("4. View Order / Bill");
            System.out.println("5. Cancel Order");
            System.out.println("6. View All Orders");
            System.out.println("0. Back");
            choice = Input.integer("Enter choice: ");

            try {
                switch (choice) {
                    case 1:
                        newOrder();
                        break;
                    case 2:
                        addItem();
                        break;
                    case 3:
                        orders.removeItem(Input.integer("Order ID: "), Input.integer("Menu item ID: "));
                        System.out.println("Item removed.");
                        break;
                    case 4:
                        viewBill(Input.integer("Order ID: "));
                        break;
                    case 5:
                        orders.cancelOrder(Input.integer("Order ID: "));
                        System.out.println("Order cancelled, table freed.");
                        break;
                    case 6:
                        if (orders.getAll().isEmpty()) {
                            System.out.println("No orders yet.");
                        }
                        for (Order order : orders.getAll()) {
                            System.out.println(order);
                        }
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

    private void newOrder() {
        System.out.println("--- TABLES ---");
        for (Table table : tables.getAll()) {
            System.out.println(table);
        }
        int tableId = Input.integer("Choose table ID: ");
        Order order = orders.createOrder(tableId);
        System.out.println("Created Order #" + order.getId() + " for Table " + tableId);

        String more;
        do {
            addItemTo(order.getId());
            more = Input.text("Add another item? (y/n): ");
        } while (more.equalsIgnoreCase("y"));
    }

    private void addItem() {
        addItemTo(Input.integer("Order ID: "));
    }

    private void addItemTo(int orderId) {
        menuCli.show();
        int itemId = Input.integer("Menu item ID: ");
        int qty = Input.integer("Quantity: ");
        orders.addItem(orderId, itemId, qty);
        System.out.println("Item added.");
    }

    public void viewBill(int orderId) {
        Order o = orders.getById(orderId);
        if (o == null) throw new IllegalArgumentException("Order not found.");
        System.out.println("\n--- BILL: Order #" + o.getId()
                + " (Table " + o.getTableId() + ") [" + o.getStatus() + "] ---");
        if (o.getItems().isEmpty()) {
            System.out.println("(no items)");
        }
        for (OrderItem orderItem : o.getItems()) {
            System.out.println(orderItem);
        }
        System.out.printf("TOTAL: Rs. %.2f%n", o.getTotal());
    }
}
