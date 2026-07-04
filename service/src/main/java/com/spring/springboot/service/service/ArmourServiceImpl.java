package com.spring.springboot.service.service;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.ArmourRequestDto;
import com.spring.springboot.api.dto.ArmourResponseDto;
import com.spring.springboot.service.entity.ArmourEntity;
import com.spring.springboot.service.mapper.ArmourMapper;
import com.spring.springboot.service.rabbit.ArmourEventPublisher;
import com.spring.springboot.service.repository.ArmourRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArmourServiceImpl implements ArmourService {

    private final ArmourRepository armourRepository;
    private final ArmourMapper armourMapper;
    private final ArmourEventPublisher armourEventPublisher;

    @Override
    @Transactional(readOnly = true)
    public List<ArmourResponseDto> getAllArmours(){
        return armourRepository.findAll().stream().map(armourMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ArmourResponseDto getArmourById(Long id){
        ArmourEntity armour = findArmourEntityById(id);
        return armourMapper.toDto(armour);
    }

    @Override
    @Transactional
    public ArmourResponseDto createArmour(ArmourRequestDto armourRequest){

        ArmourEntity armour = new ArmourEntity();

        armour.setArmourName(armourRequest.getArmourName());
        armour.setType(armourRequest.getType());
        armour.setDescriptionText(armourRequest.getDescription());
        armour.setCost(armourRequest.getCost());

        ArmourEntity savedArmour = armourRepository.save(armour);

        armourEventPublisher.publishArmourCreated(savedArmour.getId(), savedArmour.getArmourName(),
                savedArmour.getType());
        log.info("Armour created event sent for armour ID: {}", savedArmour.getId());

        return armourMapper.toDto(savedArmour);
    }

    @Override
    @Transactional
    public ArmourResponseDto updateArmour(Long id, ArmourRequestDto armourRequest){

        ArmourEntity armour = findArmourEntityById(id);

        armour.setArmourName(armourRequest.getArmourName());
        armour.setType(armourRequest.getType());
        armour.setDescriptionText(armourRequest.getDescription());
        armour.setCost(armourRequest.getCost());

        ArmourEntity updatedArmour = armourRepository.save(armour);

        armourEventPublisher.publishArmourUpdated(updatedArmour.getId(), updatedArmour.getArmourName(),
                updatedArmour.getType());
        log.info("Armour updated event sent for armour ID: {}", updatedArmour.getId());

        return armourMapper.toDto(updatedArmour);
    }

    @Override
    @Transactional
    public void deleteArmour(Long id){
        ArmourEntity armour = findArmourEntityById(id);
        armourRepository.delete(armour);

        armourEventPublisher.publishArmourDeleted(id);
        log.info("Armour deleted event sent for armour ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public ArmourEntity findArmourEntityById(Long id){
        return armourRepository.findById(id).orElseThrow(() -> new RuntimeException(
                ApiConstants.ARMOUR_NOT_FOUND + id));
    }
}
