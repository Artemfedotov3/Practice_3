package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.UpgradeRequestDto;
import com.spring.springboot.api.dto.UpgradeResponseDto;
import com.spring.springboot.service.entity.UpgradeEntity;

import java.util.List;

public interface UpgradeService {

    List<UpgradeResponseDto> getAllUpgrades();

    UpgradeResponseDto getUpgradeById(Long id);

    UpgradeResponseDto createUpgrade(UpgradeRequestDto unitRequest);

    UpgradeResponseDto updateUpgrade(Long id, UpgradeRequestDto unitRequest);

    void deleteUpgrade(Long id);

    UpgradeEntity findUpgradeEntityById(Long id);

    UpgradeResponseDto addUpgradeToUnit(Long unitId, UpgradeRequestDto dto);

    List<UpgradeResponseDto> getUpgradeByUnitId(Long unitId);
}
