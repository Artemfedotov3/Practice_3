package com.spring.springboot.service.repository;

import com.spring.springboot.service.entity.ExoticBeastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExoticBeastRepository extends JpaRepository<ExoticBeastEntity, Long> {

    List<ExoticBeastEntity> findByUnitId(Long unitId);

    Optional<ExoticBeastEntity> findByName(String name);

    boolean existsByName(String name);
}
