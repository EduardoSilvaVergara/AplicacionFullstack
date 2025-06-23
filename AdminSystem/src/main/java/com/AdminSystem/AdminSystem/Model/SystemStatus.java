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
@Schema(description = "Estado actual del sistema")
public class SystemStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del estado", example = "1")
    private Long id;

    @Schema(description = "Estado general del sistema", example = "OK")
    private String status;

    @Schema(description = "Detalles adicionales del estado", example = "Sistema funcionando correctamente")
    private String details;

    @Schema(description = "Fecha y hora del chequeo", example = "19-06-2025 10:00:00")
    private String checkedAt;
}