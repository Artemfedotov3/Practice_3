package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.EquipmentRequestDto;
import com.spring.springboot.api.dto.EquipmentResponseDto;
import com.spring.springboot.service.entity.EquipmentEntity;

import java.util.List;

public interface EquipmentService {

    List<EquipmentResponseDto> getAllEquipments();

    EquipmentResponseDto getEquipmentById(Long id);

    EquipmentResponseDto createEquipment(EquipmentRequestDto equipmentRequest);

    EquipmentResponseDto updateEquipment(Long id, EquipmentRequestDto equipmentRequest);

    void deleteEquipment(Long id);

    EquipmentEntity findEquipmentEntityById(Long id);

    EquipmentResponseDto addEquipmentToUnit(Long unitId, EquipmentRequestDto dto);

    List<EquipmentResponseDto> getEquipmentByUnitId(Long unitId);
}
