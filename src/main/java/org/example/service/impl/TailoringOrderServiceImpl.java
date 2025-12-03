package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.TailoringOrder;
import org.example.entity.TailoringOrderEntity;
import org.example.repository.TailoringOrderRepository;
import org.example.service.TailoringOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TailoringOrderServiceImpl implements TailoringOrderService {
    private final TailoringOrderRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<TailoringOrder> getAll() {
        List<TailoringOrder> TailoringOrderArrayList = new ArrayList<>();
        repository.findAll().forEach(entity->{
            TailoringOrderArrayList.add(mapper.map(entity, TailoringOrder.class));
        });
        return TailoringOrderArrayList;
    }

    @Override
    public void addTailoringOrder(TailoringOrder tailoringOrder) {
        System.out.println(tailoringOrder);
        repository.save(mapper.map(tailoringOrder, TailoringOrderEntity.class));
    }

    @Override
    public void deleteTailoringOrderById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public TailoringOrder searchTailoringOrderById(Integer id) {
        return mapper.map(repository.findById(id),TailoringOrder.class);


    }

    @Override
    public void updateTailoringOrderById(TailoringOrder tailoringOrder) {
        repository.save(mapper.map(tailoringOrder, TailoringOrderEntity.class));
    }
}
