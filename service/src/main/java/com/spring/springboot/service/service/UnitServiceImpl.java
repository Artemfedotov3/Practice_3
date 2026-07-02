package com.spring.springboot.service.service;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.UnitRequestDto;
import com.spring.springboot.api.dto.UnitResponseDto;
import com.spring.springboot.service.entity.UnitEntity;
import com.spring.springboot.service.mapper.UnitMapper;
import com.spring.springboot.service.rabbit.UnitEventPublisher;
import com.spring.springboot.service.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;
    private final UnitEventPublisher eventPublisher;

    @Override
    @Transactional(readOnly = true)
    public List<UnitResponseDto> getAllUnits() {
        return unitRepository.findAll().stream()
                .map(unitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UnitResponseDto getUnitById(Long id) {
        UnitEntity unit = findUnitEntityById(id);
        return unitMapper.toDto(unit);
    }

    @Override
    @Transactional
    public UnitResponseDto createUnit(UnitRequestDto unitRequest) {

        UnitEntity unit = new UnitEntity();
        unit.setName(unitRequest.getName());
        unit.setType(unitRequest.getType());
        unit.setMove_characteristic(unitRequest.getM());
        unit.setWeapon_skill_characteristic(unitRequest.getWS());
        unit.setBallistic_skill_characteristic(unitRequest.getBS());
        unit.setStrange_characteristic(unitRequest.getS());
        unit.setTafna_characteristic(unitRequest.getT());
        unit.setWound_characteristic(unitRequest.getW());
        unit.setInitiative_characteristic(unitRequest.getI());
        unit.setAttack_characteristic(unitRequest.getA());
        unit.setLeader_characteristic(unitRequest.getLD());
        unit.setCool_chek_characteristic(unitRequest.getCL());
        unit.setWillpower_characteristic(unitRequest.getWILL());
        unit.setIntellect_characteristic(unitRequest.getINTEL());
        unit.setXp(unitRequest.getXP());

        UnitEntity savedUnit = unitRepository.save(unit);


        eventPublisher.publishUnitCreated(savedUnit.getId(), savedUnit.getName(), savedUnit.getType());
        log.info("Unit created event sent for unit ID: {}", savedUnit.getId());

        return unitMapper.toDto(savedUnit);
    }

    @Override
    @Transactional
    public UnitResponseDto updateUnit(Long id, UnitRequestDto unitRequest) {
        UnitEntity unit = findUnitEntityById(id);

        unit.setName(unitRequest.getName());
        unit.setType(unitRequest.getType());
        unit.setMove_characteristic(unitRequest.getM());
        unit.setWeapon_skill_characteristic(unitRequest.getWS());
        unit.setBallistic_skill_characteristic(unitRequest.getBS());
        unit.setStrange_characteristic(unitRequest.getS());
        unit.setTafna_characteristic(unitRequest.getT());
        unit.setWound_characteristic(unitRequest.getW());
        unit.setInitiative_characteristic(unitRequest.getI());
        unit.setAttack_characteristic(unitRequest.getA());
        unit.setLeader_characteristic(unitRequest.getLD());
        unit.setCool_chek_characteristic(unitRequest.getCL());
        unit.setWillpower_characteristic(unitRequest.getWILL());
        unit.setIntellect_characteristic(unitRequest.getINTEL());
        unit.setXp(unitRequest.getXP());

        UnitEntity updatedUnit = unitRepository.save(unit);


        eventPublisher.publishUnitUpdated(updatedUnit.getId(), updatedUnit.getName(), updatedUnit.getType());
        log.info("Unit updated event sent for unit ID: {}", updatedUnit.getId());

        return unitMapper.toDto(updatedUnit);
    }

    @Override
    @Transactional
    public void deleteUnit(Long id) {
        UnitEntity unit = findUnitEntityById(id);
        unitRepository.delete(unit);


        eventPublisher.publishUnitDeleted(id);
        log.info("Unit deleted event sent for unit ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public UnitEntity findUnitEntityById(Long id) {
        return unitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ApiConstants.UNIT_NOT_FOUND + id));
    }
}