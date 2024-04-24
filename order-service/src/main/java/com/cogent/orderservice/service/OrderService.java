package com.cogent.orderservice.service;

import com.cogent.orderservice.payload.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order newOrder);
    List<Order> getAllOrders();
    Order getOrderById(String orderId);
    Order updateOrder(String orderId, Order updateOrder);
    void deleteOrder(String orderId);
}
