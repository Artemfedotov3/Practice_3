package com.spring.springboot.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    private String type;

    private String M;

    private String WS;

    private String BS;

    private String S;

    private String T;

    private String W;

    private String I;

    private String A;

    private String LD;

    private String CL;

    private String WILL;

    private String INTEL;

    private String XP;
}