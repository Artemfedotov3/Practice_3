package com.spring.springboot.service.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.EquipmentRequestDto;
import com.spring.springboot.api.dto.EquipmentResponseDto;
import com.spring.springboot.service.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping(ApiConstants.EQUIPMENT_PATH)
    public ResponseEntity<List<EquipmentResponseDto>> getAllEquipments(){
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }

    @GetMapping(ApiConstants.EQUIPMENT_BY_ID_PATH)
    public ResponseEntity<List<EquipmentResponseDto>> getAllEquipmentById(@PathVariable Long id){
        return ResponseEntity.ok(Collections.singletonList(equipmentService.getEquipmentById(id)));
    }

    @PostMapping(ApiConstants.EQUIPMENT_PATH)
    public ResponseEntity<EquipmentResponseDto> createEquipment(@RequestBody EquipmentRequestDto request){
        return ResponseEntity.ok(equipmentService.createEquipment(request));
    }

    @PutMapping(ApiConstants.EQUIPMENT_BY_ID_PATH)
    public ResponseEntity<EquipmentResponseDto> updateEquipment(@PathVariable Long id,
                                                                @RequestBody EquipmentRequestDto request){
        return ResponseEntity.ok(equipmentService.updateEquipment(id, request));
    }

    @DeleteMapping(ApiConstants.EQUIPMENT_BY_ID_PATH)
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id){
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/unit/{unitId}")
    public ResponseEntity<List<EquipmentResponseDto>> getEquipmentByUnitId(@PathVariable Long unitId){
        return ResponseEntity.ok(equipmentService.getEquipmentByUnitId(unitId));
    }
}
