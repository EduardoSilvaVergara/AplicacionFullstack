package com.AdminSystem.AdminSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AdminSystem.AdminSystem.Model.SystemStatus;

public interface SystemStatusRepository extends JpaRepository<SystemStatus, Long> {
    SystemStatus findTopByOrderByCheckedAtDesc();
}