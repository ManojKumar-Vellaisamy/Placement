package com.rest.models;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public enum Status {
        OPEN, PAID, CANCELLED
    }

    private final int id;
    private final int tableId;
    private final List<OrderItem> items = new ArrayList<>();
    private Status status = Status.OPEN;

    public Order(int id, int tableId) {
        this.id = id;
        this.tableId = tableId;
    }

    public int getId() {
        return id;
    }

    public int getTableId() {
        return tableId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(MenuItem menuItem, int qty) {
        for (OrderItem orderItem : items) {
            if (orderItem.getItem().getId() == menuItem.getId()) {
                orderItem.addQuantity(qty);
                return;
            }
        }
        items.add(new OrderItem(menuItem, qty));
    }

    public boolean removeItem(int menuItemId) {
        return items.removeIf(oi -> oi.getItem().getId() == menuItemId);
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem orderItem : items) {
            total += orderItem.getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order #" + id + " | Table " + tableId
                + " | Items: " + items.size()
                + " | Total: Rs. " + String.format("%.2f", getTotal())
                + " | " + status;
    }
}
