package com.spring.springboot.service.repository;

import com.spring.springboot.service.entity.UpgradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UpgradeRepository extends JpaRepository<UpgradeEntity, Long> {

    List<UpgradeEntity> findByUnitId(Long unitId);

    Optional<UpgradeEntity> findByUpgradeName(String name);

    boolean existsByUpgradeName(String name);
}
