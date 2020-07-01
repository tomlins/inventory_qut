package com.example.inventory.exception;

/**
 * Custom error handler for inventory GET by ID not found requests
 */
public class InventoryNotFoundException extends RuntimeException {

    public InventoryNotFoundException(Long id) {
        super("Inventory item " + id + " not found");
    }

}
