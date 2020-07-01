package com.example.inventory;

import com.example.inventory.entity.InventoryItem;
import com.example.inventory.exception.InventoryDuplicateException;
import com.example.inventory.exception.InventoryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for supplying controller with data per request
 *
 */
@Component
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Returns ALL InventoryItems or a paginated list specified by optional query params.
     *
     * If either param is specified without the other, all InventoryItems will be returned.
     * Specifying a query param of value -1 will also return all InventoryItems
     *
     * Example Usage:
     *
     * /inventory
     * will return the complete list
     *
     * /inventory?skip=1
     * will return the complete list
     *
     * /inventory?limit=1
     * will return the complete list
     *
     * /inventory?skip=-1
     * will return the complete list
     *
     * /inventory?skip=0?limit=5
     * will return the first 5 InventoryItem list
     *
     * /inventory?skip=1?limit=5
     * will skip the first 5 InventoryItem's and return the proceeding 5
     *
     * /inventory?skip=2?limit=5
     * will skip the first 10 InventoryItem's and return the proceeding 5
     *
     *
     * @param skip number of pages to skip (based on limit)
     * @param limit number of InventoryItems to return (per page)
     * @return JSON list of InventoryItem objects
     */
    public ResponseEntity<List<InventoryItem>> getAll(String skip, String limit) {

        int pageSkip = skip != null ? Integer.parseInt(skip) : -1;
        int pageSize = limit != null ? Integer.parseInt(limit) : -1;

        if (pageSize > 50)
            throw new IllegalArgumentException("Max value for limit is 50");

        List<InventoryItem> inventoryItemList = null;

        if (pageSkip == -1 || pageSize == -1) {
            inventoryItemList = inventoryRepository.findAll();
        } else {
            Pageable inventoryItemPage = PageRequest.of(pageSkip, pageSize);
            Page<InventoryItem> pagedInventoryItem = inventoryRepository.findAll(inventoryItemPage);
            inventoryItemList = pagedInventoryItem.get().collect(Collectors.toList());
        }

        return new ResponseEntity<>(inventoryItemList, HttpStatus.OK);
    }

    /**
     * @param id
     * @return InventoryItem
     * @throws InventoryNotFoundException if InventoryItem not found
     */
    public ResponseEntity<InventoryItem> getInventoryItemById(Long id) {
        Optional<InventoryItem> inventoryItem = inventoryRepository.findById(id);
        if(!inventoryItem.isPresent())
            throw new InventoryNotFoundException(id);

        return new ResponseEntity<>(inventoryItem.get(), HttpStatus.OK);
    }

    /**
     *
     * @param inventoryItem
     * @return InventoryItem
     * @throws InventoryDuplicateException if IventoryItem exists with same ID
     */
    public ResponseEntity<InventoryItem> addInventoryItem(InventoryItem inventoryItem) {
        if (inventoryRepository.existsById(inventoryItem.getId()))
            throw new InventoryDuplicateException(inventoryItem.getId());
        inventoryRepository.save(inventoryItem);

        return new ResponseEntity<>(inventoryItem, HttpStatus.CREATED);
    }

}
