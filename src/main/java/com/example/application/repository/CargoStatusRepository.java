package com.example.application.repository;

import com.example.application.entity.CargoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoStatusRepository extends JpaRepository<CargoStatus, Long> {
}
