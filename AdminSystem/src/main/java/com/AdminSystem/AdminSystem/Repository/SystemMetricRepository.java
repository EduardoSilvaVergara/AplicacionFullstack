package com.AdminSystem.AdminSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AdminSystem.AdminSystem.Model.SystemMetric;

public interface SystemMetricRepository extends JpaRepository<SystemMetric, Long> {
}