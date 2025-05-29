package com.AdminSystem.AdminSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AdminSystem.AdminSystem.Model.SystemAlert;

public interface SystemAlertRepository extends JpaRepository<SystemAlert, Long> {
}