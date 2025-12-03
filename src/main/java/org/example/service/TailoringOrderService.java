package org.example.service;

import org.example.dto.TailoringOrder;

import java.util.List;

public interface TailoringOrderService {
    List<TailoringOrder> getAll();

    void addTailoringOrder(TailoringOrder tailoringOrder);

    void deleteTailoringOrderById(Integer id);

    TailoringOrder searchTailoringOrderById(Integer id);

    void updateTailoringOrderById(TailoringOrder tailoringOrder);
}
