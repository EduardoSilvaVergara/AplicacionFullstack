package com.AdminSystem.AdminSystem.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un rol de usuario")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del rol", example = "1")
    private Long id;

    @Schema(description = "Nombre del rol", example = "ADMIN")
    private String nombre; //ejemplo: ADMIN, USUARIO, GERENTE

    @Schema(description = "Permisos asociados al rol", example = "VENTAS,INVENTARIO,REPORTES")
    private String permisos; //ejemplos: "VENTAS,INVENTARIO,REPORTES"
}