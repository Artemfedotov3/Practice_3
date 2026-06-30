package com.spring.springboot.service.repository;

import com.spring.springboot.service.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Long> {
    Optional<UnitEntity> findByName(String name);
    boolean existsByName(String name);
}