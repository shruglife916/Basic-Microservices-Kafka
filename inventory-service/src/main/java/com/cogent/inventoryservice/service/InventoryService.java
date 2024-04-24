package com.cogent.inventoryservice.service;

import com.cogent.inventoryservice.payload.Item;

import java.util.List;

public interface InventoryService {
    Item createItem(Item newItem);
    List<Item> getAllItems();
    Item getItemById(Long itemId);
    Item getItemByName(String itemName);
    Item updateItem(Long itemId, Item updateItem);
    void deleteItem(Long itemId);

}

