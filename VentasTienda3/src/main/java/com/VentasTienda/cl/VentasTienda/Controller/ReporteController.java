package com.VentasTienda.cl.VentasTienda.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VentasTienda.cl.VentasTienda.Service.ReporteService;

@RestController
@RequestMapping("api/v1/reportes")
public class ReporteController {
    
    @Autowired
    private ReporteService reporteService;

    @GetMapping("/ventas")
    public ResponseEntity<String> generarReporteVentas() {
    String reporte = reporteService.reporteVentas();
    if (reporte == null || reporte.isBlank()) {
        return ResponseEntity.badRequest().body("No hay datos de ventas.");
    }
        return ResponseEntity.ok(reporteService.reporteVentas());
    }

    @GetMapping("/inventario")
    public ResponseEntity<String> generarReporteInventario() {
    String reporte = reporteService.reporteVentas();
    if (reporte == null || reporte.isBlank()) {
        return ResponseEntity.badRequest().body("No hay datos de ventas.");
    }
        return ResponseEntity.ok(reporteService.reporteInventario());
    }

    @GetMapping("/tiendas")
    public ResponseEntity<String> generarReporteTienda() {
    String reporte = reporteService.reporteVentas();
    if (reporte == null || reporte.isBlank()) {
        return ResponseEntity.badRequest().body("No hay datos de ventas.");
    }
        return ResponseEntity.ok(reporteService.reporteTiendas());
    }





}
