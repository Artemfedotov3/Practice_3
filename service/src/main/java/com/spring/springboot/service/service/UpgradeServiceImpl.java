package com.spring.springboot.service.service;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.UpgradeRequestDto;
import com.spring.springboot.api.dto.UpgradeResponseDto;
import com.spring.springboot.service.entity.UnitEntity;
import com.spring.springboot.service.entity.UpgradeEntity;
import com.spring.springboot.service.mapper.UpgradeMapper;
import com.spring.springboot.service.rabbit.UpgradeEventPublisher;
import com.spring.springboot.service.repository.UnitRepository;
import com.spring.springboot.service.repository.UpgradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpgradeServiceImpl implements UpgradeService {

    private final UpgradeRepository upgradeRepository;
    private final UpgradeMapper upgradeMapper;
    private final UpgradeEventPublisher upgradeEventPublisher;
    private final UnitRepository unitRepository;

    @Override
    @Transactional
    public UpgradeResponseDto addUpgradeToUnit(Long unitId, UpgradeRequestDto dto){

        UnitEntity unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found with id: " + unitId));

        UpgradeEntity upgrade = new UpgradeEntity();
        upgrade.setUpgradeName(dto.getUpgradeName());
        upgrade.setUnit(unit);

        UpgradeEntity saved = upgradeRepository.save(upgrade);

        return upgradeMapper.toDto(saved);
    }

    @Override
    public List<UpgradeResponseDto> getUpgradeByUnitId(Long unitId){

        UnitEntity unit = unitRepository.findById(unitId).orElseThrow(() -> new RuntimeException("Unit not found"));

        return unit.getUpgradeList().stream().map(upgradeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UpgradeResponseDto> getAllUpgrades() {
        return upgradeRepository.findAll().stream().map(upgradeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UpgradeResponseDto getUpgradeById(Long id){
        UpgradeEntity upgrade = findUpgradeEntityById(id);
        return upgradeMapper.toDto(upgrade);
    }

    @Override
    @Transactional
    public UpgradeResponseDto createUpgrade(UpgradeRequestDto upgradeRequest){

        UnitEntity unit = unitRepository.findById(upgradeRequest.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        UpgradeEntity upgrade = new UpgradeEntity();

        upgrade.setUpgradeName(upgradeRequest.getUpgradeName());
        upgrade.setDescription(upgradeRequest.getDescription());
        upgrade.setCost(upgradeRequest.getCost());
        upgrade.setUnit(unit);

        UpgradeEntity savedUpgrade = upgradeRepository.save(upgrade);

        upgradeEventPublisher.publishUpgradeCreated(savedUpgrade.getId(), savedUpgrade.getUpgradeName());
        log.info("Exotic beast created event sent for exotic beast ID: {}", savedUpgrade.getId());

        return upgradeMapper.toDto(savedUpgrade);
    }

    @Override
    @Transactional
    public UpgradeResponseDto updateUpgrade(Long id, UpgradeRequestDto upgradeRequest){
        UnitEntity unit = unitRepository.findById(upgradeRequest.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        UpgradeEntity upgrade = findUpgradeEntityById(id);

        upgrade.setUpgradeName(upgradeRequest.getUpgradeName());
        upgrade.setDescription(upgrade.getDescription());
        upgrade.setCost(upgradeRequest.getCost());
        upgrade.setUnit(unit);

        UpgradeEntity updatedUpgrade = upgradeRepository.save(upgrade);

        upgradeEventPublisher.publishUpgradeUpdated(updatedUpgrade.getId(), updatedUpgrade.getUpgradeName());
        log.info("Exotic beast updated event sent for exotic beast ID: {}", updatedUpgrade.getId());

        return upgradeMapper.toDto(updatedUpgrade);
    }

    @Override
    @Transactional
    public void deleteUpgrade(Long id){
        UpgradeEntity upgrade = findUpgradeEntityById(id);
        upgradeRepository.delete(upgrade);

        upgradeEventPublisher.publishUpgradeDeleted(id);
        log.info("Exotic beast deleted event sent for exotic beast ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public UpgradeEntity findUpgradeEntityById(Long id){
        return upgradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ApiConstants.UPGRADE_NOT_FOUND + id));
    }
}
