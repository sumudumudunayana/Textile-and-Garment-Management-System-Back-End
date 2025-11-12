package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Inventory;
import org.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class InventoryController {
    @Autowired
    final InventoryService service;

    @GetMapping("/get-all")
    public List<Inventory> getInventory(){
        return service.getAll();
    }

    @PostMapping("/add-inventory")
    public void addInventory(@RequestBody Inventory inventory){
        log.info("updated-> {}",inventory);
        service.addInventory(inventory);
    }

    @GetMapping("/search-by-id/{id}")
    public Inventory getInventoryById(@PathVariable Integer id){
        return service.searchInventoryById(id);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteInventoryById(@PathVariable Integer id){
        service.deleteInventoryById(id);
    }
    @PutMapping("/update-inventory")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInventory(@RequestBody Inventory inventory){
        service.updateInventoryById(inventory);
    }
}
