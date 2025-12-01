package org.example.repository;

import org.example.entity.ReadyMadeOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadyMadeOrderRepository extends JpaRepository<ReadyMadeOrderEntity, Integer> {
}
