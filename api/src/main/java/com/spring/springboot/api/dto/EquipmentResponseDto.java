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
@Schema(description = "DTO with equipment data")
public class EquipmentResponseDto {

    private Long id;

    private String equipment_name;

    private String type;

    private String s;

    private String L;

    private int ST;

    private String ap;

    private int damage;

    private int cost;

    private String range;

    private String traits;

    private Long unitId;
}
