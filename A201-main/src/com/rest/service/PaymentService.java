package com.rest.service;

import com.rest.models.Order;
import com.rest.models.Payment;
import com.rest.payment.PaymentMethod;
import com.rest.repository.PaymentRepository;
import java.util.List;

public class PaymentService {

    private final PaymentRepository repository;
    private final OrderService orderService;
    private int nextPaymentId = 1;

    public PaymentService(OrderService orderService) {
        this.repository = new PaymentRepository();
        this.orderService = orderService;
    }

    /** Pay the bill of an order: pays total, closes order, frees the table. */
    public Payment payOrder(int orderId, PaymentMethod method) {
        Order order = orderService.getOpenOrder(orderId);
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order has no items to bill.");
        }
        double amount = order.getTotal();

        method.pay(amount); // runtime polymorphism

        Payment payment = new Payment(nextPaymentId++, orderId,
                amount, method.getMethodName(), "SUCCESS");
        repository.save(payment);
        orderService.markPaid(order);
        return payment;
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public Payment getPaymentById(int id) {
        return repository.findById(id);
    }
}
