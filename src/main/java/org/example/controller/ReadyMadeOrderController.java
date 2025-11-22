package org.example.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ReadyMadeOrder;
import org.example.dto.ReadyMadeOrderCreate;
import org.example.service.ReadyMadeOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readyMadeOrder")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class ReadyMadeOrderController {

    private final ReadyMadeOrderService service;

    @GetMapping("/get-all")
    public List<ReadyMadeOrder> getReadyMadeOrder(){
        return service.getAll();
    }

    @GetMapping("/get-detailed")
    public List<ReadyMadeOrder> getDetailed() {
        return service.getDetailedOrders();
    }

    @PostMapping("/create-detailed")
    @ResponseStatus(HttpStatus.CREATED)
    public ReadyMadeOrder createDetailed(@RequestBody ReadyMadeOrderCreate dto) {
        log.info("Create detailed order: {}", dto);
        return service.createDetailedOrder(dto);
    }

    @GetMapping("/search-by-id/{id}")
    public ReadyMadeOrder getReadyMadeOrderById(@PathVariable Integer id){
        return service.searchReadyMadeOrderById(id);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteReadyMadeOrderById(@PathVariable Integer id){
        service.deleteReadyMadeOrderById(id);
    }

    @PutMapping("/update-readyMadeOrder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateReadyMadeOrder(@RequestBody ReadyMadeOrder readyMadeOrder){
        service.updateReadyMadeOrderById(readyMadeOrder);
    }
}
