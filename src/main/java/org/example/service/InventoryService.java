package org.example.service;

import org.example.dto.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> getAll();

    void addInventory(Inventory inventory);

    void deleteInventoryById(Integer id);

    Inventory searchInventoryById(Integer id);

    void updateInventoryById(Inventory inventory);
}
