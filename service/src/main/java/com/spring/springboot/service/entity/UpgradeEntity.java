package com.spring.springboot.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "upgrade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpgradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "upgrade_name", nullable = false, length = 50)
    private String upgradeName;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private int cost;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private UnitEntity unit;
}
