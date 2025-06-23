package com.AdminSystem.AdminSystem.Controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import com.AdminSystem.AdminSystem.Model.SystemAlert;
import com.AdminSystem.AdminSystem.Model.SystemMetric;
import com.AdminSystem.AdminSystem.Model.SystemStatus;
import com.AdminSystem.AdminSystem.Service.MoniSistemaService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Monitorización", description = "Operaciones para monitorear el estado, alertas y métricas del sistema")
@RestController
@RequestMapping("/api/v1/monitor")
public class MoniSistemaControllerV2 {

    private final MoniSistemaService moniSistemaService;

    public MoniSistemaControllerV2(MoniSistemaService moniSistemaService) {
        this.moniSistemaService = moniSistemaService;
    }

    @Operation(summary = "Forzar un chequeo manual del sistema")
    @ApiResponse(responseCode = "200", description = "Chequeo realizado exitosamente")
    @PostMapping("/chequeo")
    public Map<String, String> checkNow() {
        moniSistemaService.checkSystem();
        return Map.of("mensaje", "Chequeo realizado exitosamente");
    }

    @Operation(summary = "Obtener el último estado del sistema")
    @ApiResponse(responseCode = "200", description = "Estado obtenido exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = SystemStatus.class)))
    @GetMapping("/estado")
    public EntityModel<SystemStatus> getStatus() {
        SystemStatus status = moniSistemaService.getLastStatus();
        return EntityModel.of(status,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MoniSistemaController.class).getStatus()).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MoniSistemaController.class).getAlerts()).withRel("alertas"),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MoniSistemaController.class).getMetrics()).withRel("metricas")
        );
    }

    @Operation(summary = "Obtener la lista de alertas del sistema")
    @ApiResponse(responseCode = "200", description = "Alertas obtenidas exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = SystemAlert.class)))
    @GetMapping("/alertas")
    public CollectionModel<EntityModel<SystemAlert>> getAlerts() {
        List<EntityModel<SystemAlert>> alertas = moniSistemaService.getAlerts().stream()
            .map(alert -> EntityModel.of(alert,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MoniSistemaController.class).getAlerts()).withSelfRel()
            ))
            .collect(Collectors.toList());
        return CollectionModel.of(alertas,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MoniSistemaController.class).getAlerts()).withSelfRel()
        );
    }

    @Operation(summary = "Obtener la lista de métricas del sistema")
    @ApiResponse(responseCode = "200", description = "Métricas obtenidas exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = SystemMetric.class)))
    @GetMapping("/metricas")
    public CollectionModel<EntityModel<SystemMetric>> getMetrics() {
        List<EntityModel<SystemMetric>> metricas = moniSistemaService.getMetrics().stream()
            .map(metric -> EntityModel.of(metric,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MoniSistemaController.class).getMetrics()).withSelfRel()
            ))
            .collect(Collectors.toList());
        return CollectionModel.of(metricas,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MoniSistemaController.class).getMetrics()).withSelfRel()
        );
    }
}