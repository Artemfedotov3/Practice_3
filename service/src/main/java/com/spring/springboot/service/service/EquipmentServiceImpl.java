package com.spring.springboot.service.service;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.EquipmentRequestDto;
import com.spring.springboot.api.dto.EquipmentResponseDto;
import com.spring.springboot.service.entity.EquipmentEntity;
import com.spring.springboot.service.entity.UnitEntity;
import com.spring.springboot.service.mapper.EquipmentMapper;
import com.spring.springboot.service.rabbit.EquipmentEventPublisher;
import com.spring.springboot.service.repository.EquipmentRepository;
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
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentEventPublisher equipmentEventPublisher;
    private final UnitRepository unitRepository;

    @Override
    @Transactional
    public EquipmentResponseDto addEquipmentToUnit(Long unitId, EquipmentRequestDto dto){

        UnitEntity unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found with id: " + unitId));

        EquipmentEntity equipment = new EquipmentEntity();
        equipment.setEquipmentName(dto.getEquipment_name());
        equipment.setType(dto.getType());
        equipment.setUnit(unit);

        EquipmentEntity saved = equipmentRepository.save(equipment);

        return equipmentMapper.toDto(saved);
    }

    @Override
    public List<EquipmentResponseDto> getEquipmentByUnitId(Long unitId){
        UnitEntity unit = unitRepository.findById(unitId).orElseThrow(() -> new RuntimeException("Unit not found"));

        return unit.getEquipmentList().stream().map(equipmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentResponseDto> getAllEquipments() {
        return equipmentRepository.findAll().stream().map(equipmentMapper::toDto).collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public EquipmentResponseDto getEquipmentById(Long id){
        EquipmentEntity equipment = findEquipmentEntityById(id);
        return equipmentMapper.toDto(equipment);
    }

    @Override
    @Transactional
    public EquipmentResponseDto createEquipment(EquipmentRequestDto equipmentRequest) {

        UnitEntity unit = unitRepository.findById(equipmentRequest.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        EquipmentEntity equipment = new EquipmentEntity();

        equipment.setEquipmentName(equipmentRequest.getEquipment_name());
        equipment.setType(equipmentRequest.getType());
        equipment.setS(equipmentRequest.getS());
        equipment.setL(equipmentRequest.getL());
        equipment.setStrange(equipment.getStrange());
        equipment.setArmor_penetration(equipmentRequest.getAp());
        equipment.setDamage(equipmentRequest.getDamage());
        equipment.setCost(equipmentRequest.getCost());
        equipment.setRange(equipmentRequest.getRange());
        equipment.setTraits(equipmentRequest.getTraits());
        equipment.setUnit(unit);

        EquipmentEntity savedEquipment = equipmentRepository.save(equipment);

        equipmentEventPublisher.publishEquipmentCreated(savedEquipment.getId(), savedEquipment.getEquipmentName(),
                savedEquipment.getType());
        log.info("Equipment created event sent for equipment ID: {}", savedEquipment.getId());

        return equipmentMapper.toDto(savedEquipment);
    }

    @Override
    @Transactional
    public EquipmentResponseDto updateEquipment(Long id, EquipmentRequestDto equipmentRequest){

        UnitEntity unit = unitRepository.findById(equipmentRequest.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        EquipmentEntity equipment = findEquipmentEntityById(id);

        equipment.setEquipmentName(equipmentRequest.getEquipment_name());
        equipment.setType(equipmentRequest.getType());
        equipment.setS(equipmentRequest.getS());
        equipment.setL(equipmentRequest.getL());
        equipment.setStrange(equipment.getStrange());
        equipment.setArmor_penetration(equipmentRequest.getAp());
        equipment.setDamage(equipmentRequest.getDamage());
        equipment.setCost(equipmentRequest.getCost());
        equipment.setRange(equipmentRequest.getRange());
        equipment.setTraits(equipmentRequest.getTraits());
        equipment.setUnit(unit);

        EquipmentEntity updatedEquipment = equipmentRepository.save(equipment);

        equipmentEventPublisher.publishEquipmentUpdated(updatedEquipment.getId(), updatedEquipment.getEquipmentName(),
                updatedEquipment.getType());
        log.info("Equipment updated event sent for equipment ID: {}", updatedEquipment.getId());

        return equipmentMapper.toDto(updatedEquipment);
    }

    @Override
    @Transactional
    public void deleteEquipment(Long id){
        EquipmentEntity equipment = findEquipmentEntityById(id);
        equipmentRepository.delete(equipment);

        equipmentEventPublisher.publishEquipmentDeleted(id);
        log.info("Equipment deleted event sent for equipment ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public EquipmentEntity findEquipmentEntityById(Long id){
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ApiConstants.EQUIPMENT_NOT_FOUND + id));
    }
}
