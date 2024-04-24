package com.cogent.orderservice.controller;

import com.cogent.orderservice.payload.Order;
import com.cogent.orderservice.payload.OrderEvent;
import com.cogent.orderservice.service.OrderProducer;
import com.cogent.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private OrderService orderService;

    @PostMapping("/placed")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);
        return "order placed successfully";
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> data = orderService.getAllOrders();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") String orderId) {
        Order data = orderService.getOrderById(orderId);
        if(data == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") String orderId,
                                             @Valid @RequestBody Order order) {
        Order data = orderService.updateOrder(orderId, order);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") String orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }
}

