package com.rest.CLI;

import com.rest.models.Payment;
import com.rest.payment.CardPayment;
import com.rest.payment.CashPayment;
import com.rest.payment.PaymentMethod;
import com.rest.payment.UPIPayment;
import com.rest.service.OrderService;
import com.rest.service.PaymentService;

public class PaymentCli {

    private final PaymentService service;
    private final OrderService orders;

    public PaymentCli(PaymentService service, OrderService orders) {
        this.service = service;
        this.orders = orders;
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n=== RESTAURANT PAYMENT SYSTEM ===");
            System.out.println("1. Pay Order Bill");
            System.out.println("2. View All Payments");
            System.out.println("3. Find Payment by ID");
            System.out.println("0. Back");
            choice = Input.integer("Enter choice: ");

            try {
                switch (choice) {
                    case 1:
                        payOrder();
                        break;
                    case 2:
                        viewAllPayments();
                        break;
                    case 3:
                        findPayment();
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

    private void payOrder() {
        int orderId = Input.integer("Order ID: ");
        System.out.printf("Amount to pay: Rs. %.2f%n", orders.getOpenOrder(orderId).getTotal());

        System.out.println("\nSelect Payment Method");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        int choice = Input.integer("Enter choice: ");

        PaymentMethod method;
        switch (choice) {
            case 1:
                method = new CashPayment();
                break;
            case 2:
                method = new CardPayment();
                break;
            case 3:
                method = new UPIPayment();
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        Payment payment = service.payOrder(orderId, method);
        System.out.println("Payment record saved. Table is now free.");
        System.out.println(payment);
    }

    private void viewAllPayments() {
        if (service.getAllPayments().isEmpty()) {
            System.out.println("No payment records found.");
            return;
        }
        for (Payment payment : service.getAllPayments()) {
            System.out.println(payment);
        }
    }

    private void findPayment() {
        Payment payment = service.getPaymentById(Input.integer("Enter payment ID: "));
        System.out.println(payment == null ? "Payment not found." : payment);
    }
}
