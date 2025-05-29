package com.VentasTienda.cl.VentasTienda.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VentasTienda.cl.VentasTienda.Model.Inventario;
import com.VentasTienda.cl.VentasTienda.Model.InventarioDetalle;
import com.VentasTienda.cl.VentasTienda.Model.Producto;
import com.VentasTienda.cl.VentasTienda.Model.Tienda;
import com.VentasTienda.cl.VentasTienda.Model.Venta;
import com.VentasTienda.cl.VentasTienda.Repository.InventarioRepository;
import com.VentasTienda.cl.VentasTienda.Repository.ProductoRepository;
import com.VentasTienda.cl.VentasTienda.Repository.TiendaRepository;
import com.VentasTienda.cl.VentasTienda.Repository.VentaRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ReporteService {
    @Autowired
    private ProductoRepository pr;
    @Autowired
    private InventarioRepository ir;
    @Autowired
    private TiendaRepository tr;
    @Autowired
    private VentaRepository vr;

    public String reporteVentas() {
    List<Venta> listaVenta = vr.findAll();
    StringBuilder textVenta = new StringBuilder("Reporte de Ventas: \n");
    double totalGanado = 0;
    for (Venta v : listaVenta) {
        textVenta.append("Venta N°").append(v.getId_venta())
                .append(" | Fecha: ").append(v.getFecha()).append("\n");
        // Recorrer detalles de la venta
        v.getDetalles().forEach(detalle -> {
            textVenta.append("   Producto: ").append(detalle.getProducto().getNombre())
                .append(" | Cantidad: ").append(detalle.getCantidad())
                .append(" | Subtotal: $").append(detalle.getSubtotal()).append("\n");
        });
        textVenta.append("   Total Venta: $").append(v.getTotal()).append("\n\n");
        totalGanado += v.getTotal();
    }
    textVenta.append("\n Total Ganado: $").append(totalGanado);
    return textVenta.toString();
    }

    public String reporteTiendas() {
    List<Tienda> listaTienda = tr.findAll();
    StringBuilder textTienda = new StringBuilder("Reporte de Tienda: \n");
    for (Tienda t : listaTienda) {
        textTienda.append("Tienda ID: ").append(t.getId_tienda())
                .append(" | Direccion: ").append(t.getDireccion())
                .append(" | Horario: ").append(t.getHorario_atencion())
                .append(" | Trabajadores: ").append(t.getTrabajadores().size()).append("\n");
    }
    return textTienda.toString();
    }

    public String reporteInventario() {
    List<Producto> productos = pr.findAll();
    List<Inventario> inventarios = ir.findAll();
    StringBuilder sb = new StringBuilder("Reporte de Inventario (Todos los Productos):\n");
    if (productos.isEmpty()) {
        sb.append("No hay productos registrados.\n");
        return sb.toString();
    }
    for (Producto producto : productos) {
        int totalCantidad = 0;
        // Sumar la cantidad de este producto en todos los inventarios
        for (Inventario inventario : inventarios) {
            if (inventario.getDetalles() != null) {
                for (InventarioDetalle detalle : inventario.getDetalles()) {
                    if (detalle.getProducto() != null && detalle.getProducto().getId_producto().equals(producto.getId_producto())) {
                        totalCantidad += detalle.getCantidad();
                    }
                }
            }
        }
        sb.append("ID: ").append(producto.getId_producto())
          .append(" | Nombre: ").append(producto.getNombre())
          .append(" | Precio: $").append(producto.getPrecio())
          .append(" | Cantidad en inventario: ").append(totalCantidad)
          .append(" | Material: ").append(producto.getMaterial())
          .append(" | Talla: ").append(producto.getTalla())
          .append(" | Fecha Vencimiento: ").append(producto.getFecha_vencimiento())
          .append(" | Refrigerar: ").append(producto.isRefrigerar())
          .append("\n");
    }
    return sb.toString();
    }



}
