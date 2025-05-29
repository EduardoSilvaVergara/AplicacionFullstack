package com.AdminSystem.AdminSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.AdminSystem.AdminSystem.Model.SystemAlert;
import com.AdminSystem.AdminSystem.Model.SystemMetric;
import com.AdminSystem.AdminSystem.Model.SystemStatus;
import com.AdminSystem.AdminSystem.Service.MoniSistemaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/monitor")
public class MoniSistemaController {

    @Autowired
    private MoniSistemaService moniSistemaService;

    // Endpoint para forzar chequeo manual
    @PostMapping("/chequeo")
    public void checkNow() {
        moniSistemaService.checkSystem();
    }


    @GetMapping("/estado")
    public SystemStatus getStatus() {
        return moniSistemaService.getLastStatus();
    }

    @GetMapping("/alertas")
    public List<SystemAlert> getAlerts() {
        return moniSistemaService.getAlerts();
    }

    @GetMapping("/metricas")
    public List<SystemMetric> getMetrics() {
        return moniSistemaService.getMetrics();
    }

    
}