package com.spring.springboot.service.service;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.ExoticBeastRequestDto;
import com.spring.springboot.api.dto.ExoticBeastResponseDto;
import com.spring.springboot.service.entity.ExoticBeastEntity;
import com.spring.springboot.service.entity.UnitEntity;
import com.spring.springboot.service.mapper.ExoticBeastMapper;
import com.spring.springboot.service.rabbit.ExoticBeastEventPublisher;
import com.spring.springboot.service.repository.ExoticBeastRepository;
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
public class ExoticBeastServiceImpl implements ExoticBeastService{

    private final ExoticBeastRepository exoticBeastRepository;
    private final ExoticBeastMapper exoticBeastMapper;
    private final ExoticBeastEventPublisher exoticBeastEventPublisher;
    private final UnitRepository unitRepository;

    @Override
    @Transactional
    public ExoticBeastResponseDto addExoticBeastToUnit(Long unitId, ExoticBeastRequestDto dto){

        UnitEntity unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found with id: " + unitId));

        ExoticBeastEntity exoticBeast = new ExoticBeastEntity();
        exoticBeast.setName(dto.getName());
        exoticBeast.setUnit(unit);

        ExoticBeastEntity saved = exoticBeastRepository.save(exoticBeast);

        return exoticBeastMapper.toDto(saved);
    }

    @Override
    public List<ExoticBeastResponseDto> getExoticBeastByUnitId(Long unitId){

        UnitEntity unit = unitRepository.findById(unitId).orElseThrow(() -> new RuntimeException("Unit not found"));

        return unit.getExoticBeastList().stream().map(exoticBeastMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExoticBeastResponseDto> getAllExoticBeasts() {
        return exoticBeastRepository.findAll().stream().map(exoticBeastMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ExoticBeastResponseDto getExoticBeastById(Long id){
        ExoticBeastEntity exoticBeast = findExoticBeastEntityById(id);
        return exoticBeastMapper.toDto(exoticBeast);
    }

    @Override
    @Transactional
    public ExoticBeastResponseDto createExoticBeast(ExoticBeastRequestDto exoticBeastRequest){

        UnitEntity unit = unitRepository.findById(exoticBeastRequest.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        ExoticBeastEntity exoticBeast = new ExoticBeastEntity();

        exoticBeast.setName(exoticBeastRequest.getName());
        exoticBeast.setDescription(exoticBeastRequest.getDescription());
        exoticBeast.setMove(exoticBeastRequest.getMove());
        exoticBeast.setCost(exoticBeastRequest.getCost());
        exoticBeast.setUnit(unit);

        ExoticBeastEntity savedExoticBeast = exoticBeastRepository.save(exoticBeast);

        exoticBeastEventPublisher.publishExoticBeastCreated(savedExoticBeast.getId(), savedExoticBeast.getName());
        log.info("Exotic beast created event sent for exotic beast ID: {}", savedExoticBeast.getId());

        return exoticBeastMapper.toDto(savedExoticBeast);
    }

    @Override
    @Transactional
    public ExoticBeastResponseDto updateExoticBeast(Long id, ExoticBeastRequestDto exoticBeastRequest){
        UnitEntity unit = unitRepository.findById(exoticBeastRequest.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        ExoticBeastEntity exoticBeast = findExoticBeastEntityById(id);

        exoticBeast.setName(exoticBeastRequest.getName());
        exoticBeast.setDescription(exoticBeast.getDescription());
        exoticBeast.setMove(exoticBeastRequest.getMove());
        exoticBeast.setCost(exoticBeastRequest.getCost());
        exoticBeast.setUnit(unit);

        ExoticBeastEntity updatedExoticBeast = exoticBeastRepository.save(exoticBeast);

        exoticBeastEventPublisher.publishExoticBeastUpdated(updatedExoticBeast.getId(), updatedExoticBeast.getName());
        log.info("Exotic beast updated event sent for exotic beast ID: {}", updatedExoticBeast.getId());

        return exoticBeastMapper.toDto(updatedExoticBeast);
    }

    @Override
    @Transactional
    public void deleteExoticBeast(Long id){
        ExoticBeastEntity exoticBeast = findExoticBeastEntityById(id);
        exoticBeastRepository.delete(exoticBeast);

        exoticBeastEventPublisher.publishExoticBeastDeleted(id);
        log.info("Exotic beast deleted event sent for exotic beast ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public ExoticBeastEntity findExoticBeastEntityById(Long id){
        return exoticBeastRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ApiConstants.EXOTIC_BEAST_NOT_FOUND + id));
    }
}
