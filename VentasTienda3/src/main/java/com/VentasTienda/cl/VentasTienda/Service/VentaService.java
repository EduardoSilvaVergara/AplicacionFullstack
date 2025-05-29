package com.VentasTienda.cl.VentasTienda.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VentasTienda.cl.VentasTienda.Model.Producto;
import com.VentasTienda.cl.VentasTienda.Model.Venta;
import com.VentasTienda.cl.VentasTienda.Model.VentaDetalle;
import com.VentasTienda.cl.VentasTienda.Repository.ProductoRepository;
import com.VentasTienda.cl.VentasTienda.Repository.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository vr;
    @Autowired
    private ProductoRepository pr;

    public Venta registrarVenta(List<Long> productoIds, List<Integer> cantidades) {
    if (productoIds == null || cantidades == null || productoIds.size() != cantidades.size()) return null;

    double totalFinal = 0.0;
    List<VentaDetalle> detalles = new ArrayList<>();

    for (int i = 0; i < productoIds.size(); i++) {
        Producto producto = pr.findById(productoIds.get(i)).orElse(null);
        if (producto == null) continue;
        int cantidad = cantidades.get(i);

        double descuentoPorcentaje = (cantidad >= 10) ? 10.0 : 0.0;
        double precioUnitario = producto.getPrecio();
        double subTotal = precioUnitario * cantidad;
        double descuento = subTotal * (descuentoPorcentaje / 100);
        double total = subTotal - descuento;

        VentaDetalle detalle = new VentaDetalle();
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setSubtotal(total);

        detalles.add(detalle);
        totalFinal += total;
    }

    Venta venta = new Venta();
    venta.setDetalles(detalles);
    venta.setTotal(Math.round(totalFinal));
    venta.setFecha(LocalDate.now().toString());

    return vr.save(venta);
    }

    public List<Venta> listarVentas() {
        return vr.findAll();
    }

    public Venta buscarVenta(Long id) {
        return vr.findById(id).orElse(null);
    }

    public Boolean eliminarVenta(Long id) {
        if (vr.existsById(id)) {
            vr.deleteById(id);
            return true;
        }
        return false;
    }








}
