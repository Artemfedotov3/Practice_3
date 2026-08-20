package com.spring.springboot.service.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.UpgradeRequestDto;
import com.spring.springboot.api.dto.UpgradeResponseDto;
import com.spring.springboot.service.service.UpgradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UpgradeController {

    private final UpgradeService upgradeService;

    @GetMapping(ApiConstants.UPGRADE_PATH)
    public ResponseEntity<List<UpgradeResponseDto>> getAllUpgrades() {
        return ResponseEntity.ok(upgradeService.getAllUpgrades());
    }

    @GetMapping(ApiConstants.UPGRADE_BY_ID_PATH)
    public ResponseEntity<UpgradeResponseDto> getUpgradeById(@PathVariable Long id) {
        return ResponseEntity.ok(upgradeService.getUpgradeById(id));
    }

    @PostMapping(ApiConstants.UPGRADE_PATH)
    public ResponseEntity<UpgradeResponseDto> createUpgrade(@RequestBody UpgradeRequestDto request) {
        return ResponseEntity.ok(upgradeService.createUpgrade(request));
    }

    @PutMapping(ApiConstants.UPGRADE_BY_ID_PATH)
    public ResponseEntity<UpgradeResponseDto> updateUpgrade(@PathVariable Long id,
                                                            @RequestBody UpgradeRequestDto request) {
        return ResponseEntity.ok(upgradeService.updateUpgrade(id, request));
    }

    @DeleteMapping(ApiConstants.UPGRADE_BY_ID_PATH)
    public ResponseEntity<Void> deleteUpgrade(@PathVariable Long id) {
        upgradeService.deleteUpgrade(id);
        return ResponseEntity.noContent().build();
    }
}
