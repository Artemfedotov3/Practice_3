package com.spring.springboot.api.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.UnitRequestDto;
import com.spring.springboot.api.dto.UpgradeRequestDto;
import com.spring.springboot.api.dto.UpgradeResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UpgradeApi {

    @GetMapping(ApiConstants.UPGRADE_PATH)
    ResponseEntity<List<UpgradeResponseDto>> getAllUpgrades();

    @GetMapping(ApiConstants.UPGRADE_BY_ID_PATH)
    ResponseEntity<List<UpgradeResponseDto>> getUpgradeById(@PathVariable("id") Long id);

    @PostMapping(ApiConstants.UPGRADE_PATH)
    ResponseEntity<UpgradeResponseDto> createUpgrade(@Valid @RequestBody UnitRequestDto upgrateRequest);

    @PutMapping(ApiConstants.UPGRADE_BY_ID_PATH)
    ResponseEntity<UpgradeResponseDto> updateUpgrade(@PathVariable("id") Long id, @Valid @RequestBody
    UpgradeRequestDto upgradeRequest);

    @DeleteMapping(ApiConstants.UPGRADE_BY_ID_PATH)
    ResponseEntity<Void> deleteUpgrade(@PathVariable("id") Long id);
}
