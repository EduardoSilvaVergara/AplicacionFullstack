package com.AdminSystem.AdminSystem.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un usuario del sistema")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del usuario", example = "1")
    private Long id;

    @Schema(description = "Nombre de usuario", example = "juanperez")
    private String nombreUsuario;
    
    @Schema(description = "Correo electrónico del usuario", example = "juan@mail.com")
    private String correo;

    @Schema(description = "Contraseña del usuario", example = "123456")
    private String contrasena;

    @ManyToOne
    @Schema(description = "Rol asignado al usuario")
    private Rol rol;
}