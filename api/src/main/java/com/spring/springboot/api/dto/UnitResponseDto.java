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

    private String m;       // Movement

    private String ws;      // Weapon Skill

    private String bs;      // Ballistic Skill

    private String s;       // Strength

    private String t;       // Toughness

    private String w;       // Wounds

    private String i;       // Initiative

    private String a;       // Attacks

    private String ld;      // Leadership

    private String cl;      // Cool

    private String will;    // Willpower

    private String intel;   // Intelligence

    private String xp;      // Experience Points
}