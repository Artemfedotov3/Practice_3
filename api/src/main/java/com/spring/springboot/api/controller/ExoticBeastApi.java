package com.spring.springboot.api.controller;

import com.spring.springboot.api.constants.ApiConstants;
import com.spring.springboot.api.dto.ExoticBeastRequestDto;
import com.spring.springboot.api.dto.ExoticBeastResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ExoticBeastApi {

    @GetMapping(ApiConstants.EXOTIC_BEAST_PATH)
    ResponseEntity<List<ExoticBeastResponseDto>> getAllExoticBeasts();

    @GetMapping(ApiConstants.EXOTIC_BEAST_BY_ID_PATH)
    ResponseEntity<List<ExoticBeastResponseDto>> getExoticBeastById(@PathVariable("id") Long id);

    @PostMapping(ApiConstants.EXOTIC_BEAST_PATH)
    ResponseEntity<List<ExoticBeastResponseDto>> createdExoticBeast(
            @Valid @RequestBody ExoticBeastRequestDto exoticBeastRequest);

    @PutMapping(ApiConstants.EXOTIC_BEAST_BY_ID_PATH)
    ResponseEntity<List<ExoticBeastResponseDto>> updateExoticBeast(@PathVariable("id") Long id, @Valid @RequestBody
                                                                    ExoticBeastRequestDto exoticBeastRequest);

    @DeleteMapping(ApiConstants.EXOTIC_BEAST_BY_ID_PATH)
    ResponseEntity<Void> deleteExoticBeast(@PathVariable("id") Long id);
}
