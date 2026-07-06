package com.spring.springboot.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "Will")
    private String willpower_characteristic;

    @Column(name = "Intel")
    private String intellect_characteristic;

    @Column(name = "XP")
    private String xp;

    @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY)
    private List<EquipmentEntity> equipmentList = new ArrayList<>();

    @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY)
    private List<ArmourEntity> armourList = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}