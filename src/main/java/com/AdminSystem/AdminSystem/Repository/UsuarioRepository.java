package com.AdminSystem.AdminSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AdminSystem.AdminSystem.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByNombreUsuario(String nombreUsuario);
}