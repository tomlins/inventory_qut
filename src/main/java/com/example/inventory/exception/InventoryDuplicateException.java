package com.example.inventory.exception;

/**
 * Custom exception handler for reporting duplicate inventory item POST requests
 */
public class InventoryDuplicateException extends RuntimeException {

    public InventoryDuplicateException(Long id) {
        super("Inventory item " + id + " exists already");
    }

}
