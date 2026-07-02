package com.spring.springboot.service.repository;

import com.spring.springboot.service.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {

    Optional<EquipmentEntity> findByEquipmentName(String name);

    boolean existsByEquipmentName(String name);
}
