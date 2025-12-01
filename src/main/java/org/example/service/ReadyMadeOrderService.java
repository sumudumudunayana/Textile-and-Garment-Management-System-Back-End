package org.example.service;

import jakarta.transaction.Transactional;
import org.example.dto.ReadyMadeOrder;
import org.example.dto.ReadyMadeOrderCreate;

import java.util.List;

public interface ReadyMadeOrderService {
    List<ReadyMadeOrder> getAll();

    void deleteReadyMadeOrderById(Integer id);

    ReadyMadeOrder searchReadyMadeOrderById(Integer id);

    void updateReadyMadeOrderById(ReadyMadeOrder readyMadeOrder);

    @Transactional
    List<ReadyMadeOrder> getDetailedOrders();

    @Transactional
    ReadyMadeOrder createDetailedOrder(ReadyMadeOrderCreate dto);
}
