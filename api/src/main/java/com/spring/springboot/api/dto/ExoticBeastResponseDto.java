package com.spring.springboot.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO with exotic beast data")
public class ExoticBeastResponseDto {

    private Long id;

    private String name;

    private String description;

    private int move;

    private int cost;

    private Long unitId;
}
