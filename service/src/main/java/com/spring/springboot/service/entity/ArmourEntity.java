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
@Table(name = "armour")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArmourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "armor_name")
    private String armourName;

    @Column(name = "type_value")
    private String type;

    @Column(name = "description_text")
    private String descriptionText;

    @Column(name = "cost")
    private int cost;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private UnitEntity unit;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
