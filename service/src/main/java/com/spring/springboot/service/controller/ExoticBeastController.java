package com.spring.springboot.service.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.ExoticBeastRequestDto;
import com.spring.springboot.api.dto.ExoticBeastResponseDto;
import com.spring.springboot.service.service.ExoticBeastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExoticBeastController {

    private final ExoticBeastService exoticBeastService;

    @GetMapping(ApiConstants.EXOTIC_BEAST_PATH)
    public ResponseEntity<List<ExoticBeastResponseDto>> getAllExoticBeasts(){
        return ResponseEntity.ok(exoticBeastService.getAllExoticBeasts());
    }

    @GetMapping(ApiConstants.EXOTIC_BEAST_BY_ID_PATH)
    public ResponseEntity<List<ExoticBeastResponseDto>> getAllExoticBeastById(@PathVariable Long id){
        return ResponseEntity.ok(Collections.singletonList(exoticBeastService.getExoticBeastById(id)));
    }

    @PostMapping(ApiConstants.EXOTIC_BEAST_PATH)
    public ResponseEntity<List<ExoticBeastResponseDto>> createExoticBeast(@RequestBody ExoticBeastRequestDto request){
        return  ResponseEntity.ok(Collections.singletonList(exoticBeastService.createExoticBeast(request)));
    }

    @PutMapping(ApiConstants.EXOTIC_BEAST_BY_ID_PATH)
    public ResponseEntity<List<ExoticBeastResponseDto>> updateExoticBeast(@PathVariable Long id, @RequestBody
                                                                          ExoticBeastRequestDto request){
        return ResponseEntity.ok(Collections.singletonList(exoticBeastService.updateExoticBeast(id, request)));
    }

    @DeleteMapping(ApiConstants.EXOTIC_BEAST_BY_ID_PATH)
    public ResponseEntity<Void> deleteExoticBeast(@PathVariable Long id){
        exoticBeastService.deleteExoticBeast(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exotic_beast_unit/{unitId}")
    public ResponseEntity<List<ExoticBeastResponseDto>> getExoticBeastUnitId(@PathVariable Long unitId){
        return ResponseEntity.ok(exoticBeastService.getExoticBeastByUnitId(unitId));
    }
}
