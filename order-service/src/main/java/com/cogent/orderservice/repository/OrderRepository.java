package com.cogent.orderservice.repository;

import com.cogent.orderservice.payload.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String>{
}
