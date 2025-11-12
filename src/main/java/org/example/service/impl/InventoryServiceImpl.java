package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Inventory;
import org.example.entity.InventoryEntity;
import org.example.repository.InventoryRepository;
import org.example.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Inventory> getAll() {
        List<Inventory> InventoryArrayList = new ArrayList<>();
        repository.findAll().forEach(entity->{
            InventoryArrayList.add(mapper.map(entity, Inventory.class));
        });
        return InventoryArrayList;
    }

    @Override
    public void addInventory(Inventory inventory) {
        System.out.println(inventory);
        repository.save(mapper.map(inventory, InventoryEntity.class));
    }

    @Override
    public void deleteInventoryById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Inventory searchInventoryById(Integer id) {
        return mapper.map(repository.findById(id),Inventory.class);


    }

    @Override
    public void updateInventoryById(Inventory inventory) {
        repository.save(mapper.map(inventory, InventoryEntity.class));
    }
}
