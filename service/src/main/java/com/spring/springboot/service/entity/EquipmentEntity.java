package com.spring.springboot.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipment_name", nullable = false, length = 50)
    private String equipmentName;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(name = "S")
    private String S;

    @Column(name = "L")
    private String L;

    @Column(name = "ST")
    private int strange;

    @Column(name = "ap")
    private String armor_penetration;

    @Column(name = "damage")
    private int damage;

    @Column(name = "cost")
    private int cost;

    @Column(name = "range_value")
    private String range;

    @Column(name = "traits")
    private String Traits;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private UnitEntity unit;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
