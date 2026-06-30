package com.spring.springboot.service.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.UnitRequestDto;
import com.spring.springboot.api.dto.UnitResponseDto;
import com.spring.springboot.service.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping(ApiConstants.UNIT_PATH)
    public ResponseEntity<List<UnitResponseDto>> getAllUnits() {
        return ResponseEntity.ok(unitService.getAllUnits());
    }

    @GetMapping(ApiConstants.UNIT_BY_ID_PATH)
    public ResponseEntity<UnitResponseDto> getUnitById(@PathVariable Long id) {
        return ResponseEntity.ok(unitService.getUnitById(id));
    }

    @PostMapping(ApiConstants.UNIT_PATH)
    public ResponseEntity<UnitResponseDto> createUnit(@RequestBody UnitRequestDto request) {
        return ResponseEntity.ok(unitService.createUnit(request));
    }

    @PutMapping(ApiConstants.UNIT_BY_ID_PATH)
    public ResponseEntity<UnitResponseDto> updateUnit(@PathVariable Long id, @RequestBody UnitRequestDto request) {
        return ResponseEntity.ok(unitService.updateUnit(id, request));
    }

    @DeleteMapping(ApiConstants.UNIT_BY_ID_PATH)
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }
}
