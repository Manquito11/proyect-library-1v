package com.mqgroup24.lectura360.repository;

import com.mqgroup24.lectura360.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    
}
