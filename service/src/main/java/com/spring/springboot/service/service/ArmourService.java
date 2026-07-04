package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.ArmourRequestDto;
import com.spring.springboot.api.dto.ArmourResponseDto;
import com.spring.springboot.service.entity.ArmourEntity;

import java.util.List;

public interface ArmourService {

    List<ArmourResponseDto> getAllArmours();

    ArmourResponseDto getArmourById(Long id);

    ArmourResponseDto createArmour(ArmourRequestDto armourRequest);

    ArmourResponseDto updateArmour(Long id, ArmourRequestDto armourRequest);

    void deleteArmour(Long id);

    ArmourEntity findArmourEntityById(Long id);
}
