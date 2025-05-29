package com.AdminSystem.AdminSystem.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdminSystem.AdminSystem.Model.SystemAlert;
import com.AdminSystem.AdminSystem.Model.SystemMetric;
import com.AdminSystem.AdminSystem.Model.SystemStatus;
import com.AdminSystem.AdminSystem.Repository.SystemAlertRepository;
import com.AdminSystem.AdminSystem.Repository.SystemMetricRepository;
import com.AdminSystem.AdminSystem.Repository.SystemStatusRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class MoniSistemaService {

    @Autowired
    private SystemStatusRepository statusRepo;
    @Autowired
    private SystemAlertRepository alertRepo;
    @Autowired
    private SystemMetricRepository metricRepo;

    private final Random random = new Random();

    public void checkSystem() {
        // Simulación de métricas
        double cpu = 10 + random.nextDouble() * 90;
        double mem = 10 + random.nextDouble() * 90;
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")); 

        // Guardar métricas
        SystemMetric metric = new SystemMetric(null, now, cpu, mem);
        metricRepo.save(metric);

        // Estado y alertas
        String status = "OK";
        String details = "Sistema funcionando correctamente";
        if (cpu > 80 || mem > 80) {
            status = "WARNING";
            details = "Uso elevado de recursos";
            alertRepo.save(new SystemAlert(null, "CPU: " + cpu + "%, MEM: " + mem + "%", "HIGH", now));
        }

        statusRepo.save(new SystemStatus(null, status, details, now));
    }

    public SystemStatus getLastStatus() {
        return statusRepo.findTopByOrderByCheckedAtDesc();
    }

    public List<SystemAlert> getAlerts() {
        return alertRepo.findAll();
    }

    public List<SystemMetric> getMetrics() {
        return metricRepo.findAll();
    }
}