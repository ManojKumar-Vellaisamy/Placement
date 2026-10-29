package com.rest.service;

import com.rest.models.MenuItem;
import com.rest.models.Order;
import com.rest.repository.OrderRepository;
import java.util.List;

/** Connects Tables + Menu + Orders. */
public class OrderService {

    private final OrderRepository repository = new OrderRepository();
    private final TableServices tableService;
    private final MenuService menuService;
    private int nextOrderId = 1;

    public OrderService(TableServices tableService, MenuService menuService) {
        this.tableService = tableService;
        this.menuService = menuService;
    }

    public Order createOrder(int tableId) {
        if (tableService.getById(tableId) == null) {
            throw new IllegalArgumentException("Table not found.");
        }
        if (!tableService.isFree(tableId)) {
            throw new IllegalArgumentException("Table " + tableId + " is already occupied.");
        }
        tableService.occupy(tableId);
        Order order = new Order(nextOrderId++, tableId);
        repository.save(order);
        return order;
    }

    public void addItem(int orderId, int menuItemId, int qty) {
        Order order = getOpenOrder(orderId);
        MenuItem item = menuService.getById(menuItemId);
        if (item == null) {
            throw new IllegalArgumentException("Menu item not found.");
        }
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        order.addItem(item, qty);
    }

    public void removeItem(int orderId, int menuItemId) {
        Order order = getOpenOrder(orderId);
        if (!order.removeItem(menuItemId)) {
            throw new IllegalArgumentException("Item is not in this order.");
        }
    }

    public void cancelOrder(int orderId) {
        Order order = getOpenOrder(orderId);
        order.setStatus(Order.Status.CANCELLED);
        tableService.free(order.getTableId());
    }

    /** Called by PaymentService after successful payment. */
    public void markPaid(Order order) {
        order.setStatus(Order.Status.PAID);
        tableService.free(order.getTableId());
    }

    public Order getOpenOrder(int orderId) {
        Order order = repository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (order.getStatus() != Order.Status.OPEN) {
            throw new IllegalArgumentException("Order #" + orderId + " is " + order.getStatus() + ".");
        }
        return order;
    }

    public Order getById(int id) {
        return repository.findById(id);
    }

    public List<Order> getAll() {
        return repository.findAll();
    }
}
