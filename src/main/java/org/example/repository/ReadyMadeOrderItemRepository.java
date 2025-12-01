package org.example.repository;

import org.example.entity.ReadyMadeOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadyMadeOrderItemRepository extends JpaRepository<ReadyMadeOrderItemEntity, Integer> {

    List<ReadyMadeOrderItemEntity> findByOrder_ReadyMadeOrderId(Integer readyMadeOrderId);
}
