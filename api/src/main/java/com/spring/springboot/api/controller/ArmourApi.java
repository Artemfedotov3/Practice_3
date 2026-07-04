package com.spring.springboot.api.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.ArmourRequestDto;
import com.spring.springboot.api.dto.ArmourResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ArmourApi {

    @GetMapping(ApiConstants.ARMOUR_PATH)
    ResponseEntity<List<ArmourResponseDto>> getAllArmours();

    @GetMapping(ApiConstants.ARMOUR_BY_ID_PATH)
    ResponseEntity<List<ArmourResponseDto>> getArmourById(@PathVariable("id") Long id);

    @PostMapping(ApiConstants.ARMOUR_PATH)
    ResponseEntity<List<ArmourResponseDto>> createdArmour(@Valid @RequestBody ArmourRequestDto armourRequest);

    @PutMapping(ApiConstants.ARMOUR_BY_ID_PATH)
    ResponseEntity<List<ArmourResponseDto>> updateArmour(@PathVariable("id") Long id,
                                                         @Valid @RequestBody ArmourRequestDto armourRequest);

    @DeleteMapping(ApiConstants.ARMOUR_BY_ID_PATH)
    ResponseEntity<Void> deleteArmour(@PathVariable("id") Long id);
}
