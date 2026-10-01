package com.saga.inventory.service;

import com.saga.inventory.dto.InventoryRequest;
import com.saga.inventory.entity.Inventory;
import com.saga.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory reserve(InventoryRequest request) {

        Inventory inventory = inventoryRepository
                .findByProductId(request.getProductId())
                .orElseThrow(
                        () -> new RuntimeException(
                                "Product not found"
                        )
                );

        if (inventory.getAvailableQuantity() < request.getQuantity()) {

            System.out.println(
                    "Inventory reservation FAILED for product: "
                            + request.getProductId()
            );

            throw new RuntimeException(
                    "Insufficient inventory"
            );
        }

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity()
                        - request.getQuantity()
        );

        System.out.println(
                "Inventory reservation SUCCESS for product: "
                        + request.getProductId()
        );

        return inventoryRepository.save(inventory);
    }

    public Inventory release(
            String productId,
            int quantity) {

        Inventory inventory = inventoryRepository
                .findByProductId(productId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Product not found"
                        )
                );

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() + quantity
        );

        return inventoryRepository.save(inventory);
    }
}
