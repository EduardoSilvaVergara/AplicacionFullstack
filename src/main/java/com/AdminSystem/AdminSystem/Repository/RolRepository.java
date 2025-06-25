package com.AdminSystem.AdminSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AdminSystem.AdminSystem.Model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}