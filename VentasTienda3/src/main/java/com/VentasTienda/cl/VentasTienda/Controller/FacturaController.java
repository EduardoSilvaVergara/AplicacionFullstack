package com.VentasTienda.cl.VentasTienda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.VentasTienda.cl.VentasTienda.Model.Factura;
import com.VentasTienda.cl.VentasTienda.Service.FacturaService;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/generar")
    public ResponseEntity<Factura> generarFactura(@RequestParam Long nroVenta, @RequestParam String email) {
        Factura factura = facturaService.generarYGuardarFactura(nroVenta, email);
        if (factura == null) {
            return ResponseEntity.badRequest().build(); //error 400
        }
        return ResponseEntity.ok(factura);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Factura>> listarFacturas() {
    List<Factura> facturas = facturaService.listarFacturas();
    if (facturas.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(facturas);
    }
}
