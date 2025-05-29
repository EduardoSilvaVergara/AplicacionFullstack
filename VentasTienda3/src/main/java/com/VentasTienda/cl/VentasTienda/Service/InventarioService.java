package com.VentasTienda.cl.VentasTienda.Service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VentasTienda.cl.VentasTienda.Model.Producto;
import com.VentasTienda.cl.VentasTienda.Repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService {

    @Autowired
    private ProductoRepository pr;
    

    public List<Producto> ListarProductos(){
        return pr.findAll();
    }

    public Producto agregarProducto(Producto producto) {
        return pr.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado){

        //Buscar producto por ID(nro_identificador) <-- Creo
        //Optional<Producto> productoBuscado = productoRepository.findById(id);
        Producto producto = pr.findById(id).orElse(null);


        if (producto != null) { //otra opcion
        //if(productoBuscado.isPresent()) {
            // Si se encuentra, se actualiza los datos
            //producto = productoBuscado.get();
            producto.setId_producto(productoActualizado.getId_producto()); 
            producto.setFecha_vencimiento(productoActualizado.getFecha_vencimiento());
            producto.setPrecio(productoActualizado.getPrecio()); 
            // poner algo mas talves nose
            return pr.save(producto); 
        }
        else {
            //Si no encuentra manda este mensaje/null
            System.out.println("Producto no encontrado");
            return null;
        }
        
    }

    public void eliminarProducto(Long id) {
        pr.deleteById(id);
    }

    public Producto ajustarStock(Long id, String operacion, Integer cantidad) {
        //Optional<Producto> productoOpc = productoRepository.findById(id);
        Producto producto = pr.findById(id).orElse(null);
        //if (productoOpc.isPresent()) {
        if(producto != null) {
            //Producto producto = productoOpc.get();
            if( "Incrementar".equalsIgnoreCase(operacion)) {
                producto.setCantidad(producto.getCantidad() + cantidad);
            } else if ("Disminuir".equalsIgnoreCase(operacion)) {
                producto.setCantidad(producto.getCantidad() - cantidad);
            } else {
                System.out.println("Operación no válida");
                return null;
            }
            return pr.save(producto);
        } else {
            System.out.println("Producto no encontrado");
            return null;
        }
    }


    public Producto consultarPorId(Long id) {
        return pr.findById(id).orElse(null);
    }

    // ajustarStock es para inventario creo y tengo que cambiarlo, agregar Stock como dato "Recordar"
}
