package com.rest.CLI;

import com.rest.service.MenuService;
import com.rest.service.OrderService;
import com.rest.service.PaymentService;
import com.rest.service.TableServices;

public class MainCli {

    private final TableCli tableCli;
    private final MenuCli menuCli;
    private final OrderCli orderCli;
    private final PaymentCli paymentCli;

    public MainCli() {
        // Create each service ONCE and share them so everything is connected
        TableServices tableService = new TableServices();
        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService(tableService, menuService);
        PaymentService paymentService = new PaymentService(orderService);

        tableCli = new TableCli(tableService);
        menuCli = new MenuCli(menuService);
        orderCli = new OrderCli(orderService, tableService, menuService);
        paymentCli = new PaymentCli(paymentService, orderService);
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n##### RESTAURANT MANAGEMENT #####");
            System.out.println("1. Tables");
            System.out.println("2. Menu");
            System.out.println("3. Orders");
            System.out.println("4. Payments");
            System.out.println("0. Exit");
            choice = Input.integer("Enter choice: ");

            switch (choice) {
                case 1:
                    tableCli.start();
                    break;
                case 2:
                    menuCli.start();
                    break;
                case 3:
                    orderCli.start();
                    break;
                case 4:
                    paymentCli.start();
                    break;
                case 0:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
