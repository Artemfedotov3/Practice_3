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
@Schema(description = "DTO with upgrate data")
public class UpgradeResponseDto {

    private Long id;

    private String upgradeName;

    private String description;

    private int cost;

    private Long unitId;
}
