package com.cogent.inventoryservice.service.impl;

import com.cogent.inventoryservice.payload.Item;
import com.cogent.inventoryservice.payload.OrderPlacedEvent;
import com.cogent.inventoryservice.repository.InventoryRepository;
import com.cogent.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Override
    public Item createItem(Item newItem) {
        return inventoryRepository.save(newItem);
    }

    @Override
    public List<Item> getAllItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public Item getItemById(Long itemId) {
        Item item = inventoryRepository
                .findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return item;
    }

    @Override
    public Item getItemByName(String itemName) {
        Item item = inventoryRepository
                .findByName(itemName)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return item;
    }

    @Override
    public Item updateItem(Long itemId, Item updateItem) {
        Item item = inventoryRepository
                .findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setName(updateItem.getName());
        item.setQuantity(updateItem.getQuantity());
        return inventoryRepository.save(item);
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = inventoryRepository
                .findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        inventoryRepository.deleteById(itemId);

    }

}
