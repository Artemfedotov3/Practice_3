package com.spring.springboot.api.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.UnitRequestDto;
import com.spring.springboot.api.dto.UnitResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UnitApi {

    /**
     * Получить всех юнитов
     * GET /api/v1/units_models
     */
    @GetMapping(ApiConstants.UNIT_PATH)
    ResponseEntity<List<UnitResponseDto>> getAllUnits();

    /**
     * Получить юнит по ID
     * GET /api/v1/units_models/{id}
     */
    @GetMapping(ApiConstants.UNIT_BY_ID_PATH)
    ResponseEntity<UnitResponseDto> getUnitById(@PathVariable("id") Long id);

    /**
     * Создать нового юнита
     * POST /api/v1/units_models
     */
    @PostMapping(ApiConstants.UNIT_PATH)
    ResponseEntity<UnitResponseDto> createUnit(@Valid @RequestBody UnitRequestDto unitRequest);

    /**
     * Обновить юнит
     * PUT /api/v1/units_models/{id}
     */
    @PutMapping(ApiConstants.UNIT_BY_ID_PATH)
    ResponseEntity<UnitResponseDto> updateUnit(
            @PathVariable("id") Long id,
            @Valid @RequestBody UnitRequestDto unitRequest);

    /**
     * Удалить юнит
     * DELETE /api/v1/units_models/{id}
     */
    @DeleteMapping(ApiConstants.UNIT_BY_ID_PATH)
    ResponseEntity<Void> deleteUnit(@PathVariable("id") Long id);
}
