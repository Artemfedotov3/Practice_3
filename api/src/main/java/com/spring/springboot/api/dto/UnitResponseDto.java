package com.spring.springboot.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO with unit data")
public class UnitResponseDto {

    private Long id;

    private String name;

    private String type;

    private String m;

    private String ws;

    private String bs;

    private String s;

    private String t;

    private String w;

    private String i;

    private String a;

    private String ld;

    private String cl;

    private String will;

    private String intel;

    private String xp;
}