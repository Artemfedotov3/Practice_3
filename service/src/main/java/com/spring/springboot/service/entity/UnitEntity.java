package com.spring.springboot.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "units_models")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(name = "M")
    private String move_characteristic;

    @Column(name = "WS")
    private String weapon_skill_characteristic;

    @Column(name = "BS")
    private String ballistic_skill_characteristic;

    @Column(name = "S")
    private String strange_characteristic;

    @Column(name = "T")
    private String tafna_characteristic;

    @Column(name = "W")
    private String wound_characteristic;

    @Column(name = "I")
    private String initiative_characteristic;

    @Column(name = "A")
    private String attack_characteristic;

    @Column(name = "LD")
    private String leader_characteristic;

    @Column(name = "CL")
    private String cool_chek_characteristic;

    @Column(name = "WILL")
    private String willpower_characteristic;

    @Column(name = "INTEL")
    private String intellect_characteristic;

    @Column(name = "XP")
    private String xp;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
