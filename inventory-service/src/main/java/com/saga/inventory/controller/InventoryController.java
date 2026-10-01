package com.saga.inventory.controller;

import com.saga.inventory.dto.InventoryRequest;
import com.saga.inventory.entity.Inventory;
import com.saga.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(
            InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    @PostMapping("/reserve")
    public Inventory reserve(
            @RequestBody InventoryRequest request) {

        return inventoryService.reserve(request);
    }

    @PutMapping("/release")
    public Inventory release(
            @RequestParam String productId,
            @RequestParam int quantity) {

        return inventoryService.release(
                productId,
                quantity
        );
    }
}
