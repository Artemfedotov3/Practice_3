package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.UnitRequestDto;
import com.spring.springboot.api.dto.UnitResponseDto;
import com.spring.springboot.service.entity.UnitEntity;

import java.util.List;

public interface UnitService {

    List<UnitResponseDto> getAllUnits();          // ✅ должен быть

    UnitResponseDto getUnitById(Long id);          // ✅ должен быть

    UnitResponseDto createUnit(UnitRequestDto unitRequest);  // ✅ должен быть

    UnitResponseDto updateUnit(Long id, UnitRequestDto unitRequest);  // ✅ должен быть

    void deleteUnit(Long id);                      // ✅ должен быть

    UnitEntity findUnitEntityById(Long id);        // ✅ должен быть
}