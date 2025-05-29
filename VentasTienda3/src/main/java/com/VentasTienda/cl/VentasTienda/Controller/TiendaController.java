package com.VentasTienda.cl.VentasTienda.Controller;

import com.VentasTienda.cl.VentasTienda.Model.Tienda;
import com.VentasTienda.cl.VentasTienda.Model.Trabajador;
import com.VentasTienda.cl.VentasTienda.Service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @PostMapping("/crear")
    public ResponseEntity<Tienda> crearTienda(@RequestBody Tienda tienda) {
        Tienda nuevaTienda = tiendaService.crearTienda(tienda);
        return ResponseEntity.ok(nuevaTienda);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tienda>> listarTiendas() {
        return ResponseEntity.ok(tiendaService.listarTiendas());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Tienda> actualizarTienda(
            @PathVariable Long id,
            @RequestParam String direccion,
            @RequestParam String horario) {
        Tienda actualizada = tiendaService.actualizarTienda(id, direccion, horario);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{tiendaId}/trabajadores")
    public ResponseEntity<String> agregarTrabajador(
            @PathVariable Long tiendaId,
            @RequestBody Trabajador trabajador) {
        String respuesta = tiendaService.agregarTrabajador(tiendaId, trabajador);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/trabajadores/{tiendaId}")
    public ResponseEntity<List<Trabajador>> listarTrabajadores(@PathVariable Long tiendaId) {
        List<Trabajador> trabajadores = tiendaService.listarTrabajadores(tiendaId);
        if (trabajadores != null) {
            return ResponseEntity.ok(trabajadores);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{tiendaId}/trabajadores/{runTrabajador}")
    public ResponseEntity<String> eliminarTrabajador(
            @PathVariable Long tiendaId,
            @PathVariable Long runTrabajador) {
        String respuesta = tiendaService.eliminarTrabajador(tiendaId, runTrabajador);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/actualizar-politica/{id}")
    public ResponseEntity<Tienda> actualizarPolitica(
            @PathVariable Long id,
            @RequestParam String politica) {
        Tienda actualizada = tiendaService.actualizarPolitica(id, politica);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}