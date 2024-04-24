package com.cogent.orderservice.service.impl;

import com.cogent.orderservice.payload.Order;
import com.cogent.orderservice.repository.OrderRepository;
import com.cogent.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return order;
    }

    @Override
    public Order updateOrder(String orderId, Order updateOrder) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderId(updateOrder.getOrderId());
        order.setName(updateOrder.getName());
        order.setDate(updateOrder.getDate());
        order.setQuantity(updateOrder.getQuantity());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.deleteById(orderId);
    }
}
