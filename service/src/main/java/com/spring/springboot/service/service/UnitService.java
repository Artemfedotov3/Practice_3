package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.UnitRequestDto;
import com.spring.springboot.api.dto.UnitResponseDto;
import com.spring.springboot.service.entity.UnitEntity;

import java.util.List;

public interface UnitService {

    List<UnitResponseDto> getAllUnits();

    UnitResponseDto getUnitById(Long id);

    UnitResponseDto createUnit(UnitRequestDto unitRequest);

    UnitResponseDto updateUnit(Long id, UnitRequestDto unitRequest);

    void deleteUnit(Long id);

    UnitEntity findUnitEntityById(Long id);
}