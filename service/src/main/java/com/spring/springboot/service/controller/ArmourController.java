package com.spring.springboot.service.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.ArmourRequestDto;
import com.spring.springboot.api.dto.ArmourResponseDto;
import com.spring.springboot.service.service.ArmourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArmourController {

    private final ArmourService armourService;

    @GetMapping(ApiConstants.ARMOUR_PATH)
    public ResponseEntity<List<ArmourResponseDto>> getAllArmours(){
        return ResponseEntity.ok(armourService.getAllArmours());
    }

    @GetMapping(ApiConstants.ARMOUR_BY_ID_PATH)
    public ResponseEntity<List<ArmourResponseDto>> getAllArmourById(@PathVariable Long id){
        return ResponseEntity.ok(Collections.singletonList(armourService.getArmourById(id)));
    }

    @PostMapping(ApiConstants.ARMOUR_PATH)
    public ResponseEntity<List<ArmourResponseDto>> createArmour(@RequestBody ArmourRequestDto request){
        return ResponseEntity.ok(Collections.singletonList(armourService.createArmour(request)));
    }

    @PutMapping(ApiConstants.ARMOUR_BY_ID_PATH)
    public ResponseEntity<List<ArmourResponseDto>> updateArmour(@PathVariable Long id,
                                                                @RequestBody ArmourRequestDto request){
        return ResponseEntity.ok(Collections.singletonList(armourService.updateArmour(id, request)));
    }

    @DeleteMapping(ApiConstants.ARMOUR_BY_ID_PATH)
    public ResponseEntity<Void> deleteArmour(@PathVariable Long id){
        armourService.deleteArmour(id);
        return ResponseEntity.noContent().build();
    }
}
