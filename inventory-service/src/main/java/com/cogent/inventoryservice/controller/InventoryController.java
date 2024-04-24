package com.cogent.inventoryservice.controller;

import com.cogent.inventoryservice.service.impl.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    // Endpoint to update inventory quantity

}
