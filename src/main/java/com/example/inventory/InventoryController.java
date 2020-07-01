package com.example.inventory;

import com.example.inventory.entity.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller
 */
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryItem>> getAll(
            @RequestParam(required = false) String skip,
            @RequestParam(required = false) String limit) {

        return inventoryService.getAll(skip, limit);
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable("id") Long id) {
        return inventoryService.getInventoryItemById(id);
    }

    @PostMapping("/inventory")
    public ResponseEntity<InventoryItem> addInventoryItem(@Valid @RequestBody InventoryItem inventoryItem) {
        return inventoryService.addInventoryItem(inventoryItem);
    }

    @GetMapping("/about")
    public String about() {
        return "Inventory Test Application For QUT by Jason Tomlins June 30th 2020";
    }
}
