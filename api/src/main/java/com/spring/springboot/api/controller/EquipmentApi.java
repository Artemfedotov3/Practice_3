package com.spring.springboot.api.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.EquipmentRequestDto;
import com.spring.springboot.api.dto.EquipmentResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EquipmentApi {

    @GetMapping(ApiConstants.EQUIPMENT_PATH)
    ResponseEntity<List<EquipmentResponseDto>> getAllEquipments();

    @GetMapping(ApiConstants.EQUIPMENT_BY_ID_PATH)
    ResponseEntity<List<EquipmentResponseDto>> getEquipmentById(@PathVariable("id") Long id);

    @PostMapping(ApiConstants.EQUIPMENT_PATH)
    ResponseEntity<List<EquipmentResponseDto>> createdEquipment(
            @Valid @RequestBody EquipmentRequestDto equipmentRequest);

    @PutMapping(ApiConstants.EQUIPMENT_BY_ID_PATH)
    ResponseEntity<List<EquipmentResponseDto>> updateEquipment(@PathVariable("id") Long id,
                                                               @Valid @RequestBody
                                                               EquipmentRequestDto equipmentRequest);

    @DeleteMapping(ApiConstants.EQUIPMENT_BY_ID_PATH)
    ResponseEntity<Void> deleteEquipment(@PathVariable("id") Long id);
}
