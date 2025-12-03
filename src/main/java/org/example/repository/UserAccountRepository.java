package org.example.repository;

import org.example.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {
    Optional<UserAccountEntity> findByUserLoginNameAndUserLoginPassword(String userLoginName, String userLoginPassword);
}
