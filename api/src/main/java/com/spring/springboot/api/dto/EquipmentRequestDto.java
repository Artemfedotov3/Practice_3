package com.spring.springboot.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 equipments")
    private String equipment_name;

    @NotBlank(message = "Type is required")
    @Size(min = 2, max = 50, message = "Type must be between 2 and 50 equipments")
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
