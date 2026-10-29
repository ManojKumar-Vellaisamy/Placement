package com.rest.repository;

import com.rest.models.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    public Order findById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public Order findOpenByTable(int tableId) {
        for (Order order : orders) {
            if (order.getTableId() == tableId
                    && order.getStatus() == Order.Status.OPEN) {
                return order;
            }
        }
        return null;
    }
}
