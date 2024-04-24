package com.cogent.inventoryservice.repository;

import com.cogent.inventoryservice.payload.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
}
