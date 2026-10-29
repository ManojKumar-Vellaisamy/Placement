package com.rest.models;

public class Payment {

    private final int id;
    private final int orderId;
    private final double amount;
    private final String method;
    private final String status;

    public Payment(int id, int orderId, double amount,
            String method, String status) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public Payment(int id, double amount, String method, String status) {
        this(id, 0, amount, method, status);
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Payment ID: " + id
                + (orderId > 0 ? ", Order: #" + orderId : "")
                + ", Amount: Rs. " + String.format("%.2f", amount)
                + ", Method: " + method
                + ", Status: " + status;
    }
}
