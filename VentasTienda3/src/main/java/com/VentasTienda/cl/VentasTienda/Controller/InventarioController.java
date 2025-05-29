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

import com.VentasTienda.cl.VentasTienda.Model.Producto;
import com.VentasTienda.cl.VentasTienda.Service.InventarioService;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos(){
        
        List<Producto> productos = inventarioService.ListarProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();//error 400
        }
        return ResponseEntity.ok(productos);

    }

    @PostMapping("/agregar")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = inventarioService.agregarProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProductos(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = inventarioService.actualizarProducto(id, producto);
        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        inventarioService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ajustar/{id}")
    public ResponseEntity<Producto> ajustarStock(@PathVariable Long id, @RequestParam String operacion, @RequestParam Integer cantidad) {

        Producto productoModificado = inventarioService.ajustarStock(id, operacion, cantidad);
        if (productoModificado != null) {
            return ResponseEntity.ok(productoModificado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Producto> consultarProducto(@PathVariable Long id) {
        Producto producto = inventarioService.consultarPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }



}
