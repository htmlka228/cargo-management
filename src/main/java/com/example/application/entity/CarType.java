package com.example.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "car_type")
public class CarType {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String name;
    private int maxWeight;

    @ManyToMany
    @JoinTable
    private Set<ItemType> allowedItemTypes;
}
