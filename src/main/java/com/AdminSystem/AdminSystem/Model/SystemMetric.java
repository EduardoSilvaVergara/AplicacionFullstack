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
@Schema(description = "Métricas del sistema en un momento específico")
public class SystemMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la métrica", example = "1")
    private Long id;

    @Schema(description = "Fecha y hora de la métrica", example = "19-06-2025 10:00:00")
    private String timestamp;

    @Schema(description = "Porcentaje de uso de CPU", example = "45.5")
    private double cpuUsage;

    @Schema(description = "Porcentaje de uso de memoria", example = "67.3")
    private double memoryUsage;
}