package com.VentasTienda.cl.VentasTienda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.VentasTienda.cl.VentasTienda.Model.Pedido;
import com.VentasTienda.cl.VentasTienda.Service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Crear nuevo pedido
    @PostMapping("/crear")
    public ResponseEntity<Pedido> crearPedido(@RequestParam Long tiendaId,@RequestParam Long proveedorId,
                                            @RequestParam List<Long> productoIds,@RequestParam List<Integer> cantidades) {
    Pedido pedido = pedidoService.crearPedido(tiendaId, proveedorId, productoIds, cantidades);
    if (pedido == null) {
        return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(pedido);
    }

    // Listar todos los pedidos
    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    // Autorizar pedido
    @PutMapping("/autorizar/{id}")
    public ResponseEntity<Pedido> autorizarPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.autorizarPedido(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    // Actualizar pedido
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id,@RequestBody Pedido nuevoPedido) {
    Pedido pedidoActualizado = pedidoService.actualizarPedido(id, nuevoPedido);
    if (pedidoActualizado == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pedidoActualizado);
    }

    // Eliminar pedido
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        boolean eliminado = pedidoService.eliminarPedido(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
