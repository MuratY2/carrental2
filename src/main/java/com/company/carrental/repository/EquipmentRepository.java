package com.company.carrental.repository;

import com.company.carrental.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findByCode(String code);
    List<Equipment> findByCodeIn(List<String> codes);
}
