package com.VentasTienda.cl.VentasTienda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.VentasTienda.cl.VentasTienda.Model.Devolucion;
import com.VentasTienda.cl.VentasTienda.Model.Reclamo;
import com.VentasTienda.cl.VentasTienda.Service.AtencionClienteService;

@RestController
@RequestMapping("/api/v1/atencion")
public class AtencionClienteController {

    @Autowired
    private AtencionClienteService atencionClienteService;

    @PostMapping("/devolucion")
    public ResponseEntity<Devolucion> registrarDevolcion(@RequestParam Long productoId,@RequestParam int cantidad,@RequestParam String motivo) {
        Devolucion devolucion = atencionClienteService.registrarDevolucion(productoId, cantidad, motivo);
        if (devolucion == null) return null;

        return ResponseEntity.ok(devolucion);
    }

    @PostMapping("/reclamo")
    public ResponseEntity<Reclamo> registrarReclamacion(@RequestParam String detalle) {
        Reclamo reclamacion = atencionClienteService.registrarReclamo(detalle);
        return ResponseEntity.ok(reclamacion);
    }

    @GetMapping("/listar/reclamo")
    public ResponseEntity<List<Reclamo>> listarReclamaciones() {
        List<Reclamo> lista = atencionClienteService.listarReclamo();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); //error 204
        }
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/reclamacion/{id}/estado")
    public ResponseEntity<Reclamo> actualizarEstado(@PathVariable Long id,@RequestParam String estado) {
        Reclamo reclamacion = atencionClienteService.actualizarEstadoReclamo(id, estado);
        if (reclamacion == null) {
            return ResponseEntity.notFound().build(); //error 404
        }
        return ResponseEntity.ok(reclamacion);
    }


}
