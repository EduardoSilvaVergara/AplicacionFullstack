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
@Schema(description = "Alerta generada por el sistema")
public class SystemAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la alerta", example = "1")
    private Long id;

    @Schema(description = "Mensaje de la alerta", example = "CPU: 85%, MEM: 90%")
    private String message;

    @Schema(description = "Nivel de severidad de la alerta", example = "HIGH")
    private String severity;

    @Schema(description = "Fecha y hora de la alerta", example = "19-06-2025 10:00:00")
    private String alertTime;
}