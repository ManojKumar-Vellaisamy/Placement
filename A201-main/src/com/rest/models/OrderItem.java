package com.rest.models;

public class OrderItem {

    private final MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantityToAdd) {
        quantity += quantityToAdd;
    }

    public double getTotal() {
        return item.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-18s x%-3d Rs. %.2f",
                item.getName(), quantity, getTotal());
    }
}
