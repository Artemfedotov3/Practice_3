package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.ExoticBeastRequestDto;
import com.spring.springboot.api.dto.ExoticBeastResponseDto;
import com.spring.springboot.service.entity.ExoticBeastEntity;

import java.util.List;

public interface ExoticBeastService {

    List<ExoticBeastResponseDto> getAllExoticBeasts();

    ExoticBeastResponseDto getExoticBeastById(Long id);

    ExoticBeastResponseDto createExoticBeast(ExoticBeastRequestDto exoticBeastRequest);

    ExoticBeastResponseDto updateExoticBeast(Long id, ExoticBeastRequestDto exoticBeastRequest);

    void deleteExoticBeast(Long id);

    ExoticBeastEntity findExoticBeastEntityById(Long id);

    ExoticBeastResponseDto addExoticBeastToUnit(Long unitId, ExoticBeastRequestDto dto);

    List<ExoticBeastResponseDto> getExoticBeastByUnitId(Long unitId);
}