package com.spring.springboot.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArmourRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 armours")
    private String armourName;

    @NotBlank(message = "Type is required")
    @Size(min = 2, max = 50, message = "Type must be between 2 and 50 armours")
    private String type;

    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 400, message = "Description must be between 2 and 400 armours")
    private String description;

    private int cost;
}
