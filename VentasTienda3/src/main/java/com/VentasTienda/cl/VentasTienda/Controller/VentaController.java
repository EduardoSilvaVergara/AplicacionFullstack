package com.VentasTienda.cl.VentasTienda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.VentasTienda.cl.VentasTienda.Model.Venta;
import com.VentasTienda.cl.VentasTienda.Service.VentaService;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/registrar")
    public ResponseEntity<Venta> registrarVenta(@RequestParam List<Long> productoIds, @RequestParam List<Integer> cantidades) {
    Venta venta = ventaService.registrarVenta(productoIds, cantidades);
    if (venta == null) {
        return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(venta);
}

    @GetMapping("/listar")
    public ResponseEntity<List<Venta>> listarVenta() {
        List<Venta> ventas = ventaService.listarVentas();
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarVenta(@PathVariable Long id) {
        Venta venta = ventaService.buscarVenta(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        boolean eliminado = ventaService.eliminarVenta(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }









}
