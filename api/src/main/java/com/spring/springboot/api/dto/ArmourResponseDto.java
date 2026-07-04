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
@Schema(description = "DTO with armour data")
public class ArmourResponseDto {

    private Long id;

    private String armourName;

    private String type;

    private String description;

    private int cost;
}
