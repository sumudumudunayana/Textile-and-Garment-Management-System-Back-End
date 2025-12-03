package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.TailoringOrder;
import org.example.service.TailoringOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tailoring_order")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class TailoringOrderController {
    @Autowired
    final TailoringOrderService service;

    @GetMapping("/get-all")
    public List<TailoringOrder> getTailoringOrder() {
        return service.getAll();
    }

    @PostMapping("/add-tailoring-order")
    public void addTailoringOrder(@RequestBody TailoringOrder tailoringOrder){
        log.info("updated-> {}",tailoringOrder);
        service.addTailoringOrder(tailoringOrder);
    }

    @GetMapping("/search-by-id/{id}")
    public TailoringOrder getTailoringOrderById(@PathVariable Integer id){
        return service.searchTailoringOrderById(id);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTailoringOrderById(@PathVariable Integer id){
        service.deleteTailoringOrderById(id);
    }

    @PutMapping("/update-tailoring-order")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateTailoringOrder(@RequestBody TailoringOrder tailoringOrder){
        service.updateTailoringOrderById(tailoringOrder);
    }
}
