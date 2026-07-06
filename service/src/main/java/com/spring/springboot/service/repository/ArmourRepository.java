package com.spring.springboot.service.repository;

import com.spring.springboot.service.entity.ArmourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArmourRepository extends JpaRepository<ArmourEntity, Long> {

    List<ArmourEntity> findByUnitId(Long unitId);

    Optional<ArmourEntity> findByArmourName(String name);

    boolean existsByArmourName(String name);
}
