package com.example.inventory;

import com.example.inventory.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * In-memory database
 *
 */
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
}
