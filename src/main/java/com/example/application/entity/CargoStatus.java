package com.example.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cargo_status")
public class CargoStatus {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private Integer statusCode;
    private String statusName;
    private String description;
}
