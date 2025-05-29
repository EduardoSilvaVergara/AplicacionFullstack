package com.VentasTienda.cl.VentasTienda.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VentasTienda.cl.VentasTienda.Model.Factura;
import com.VentasTienda.cl.VentasTienda.Model.Venta;
import com.VentasTienda.cl.VentasTienda.Repository.FacturaRepository;
import com.VentasTienda.cl.VentasTienda.Repository.VentaRepository;

@Service
public class FacturaService {


    @Autowired
    private VentaRepository vr;

    @Autowired
    private FacturaRepository fr;

    private String crearContenidoFactura(Venta venta) {
    StringBuilder sb = new StringBuilder();
    sb.append("Factura N°: ").append(venta.getId_venta()).append("\n");
    sb.append("Productos:\n");
    venta.getDetalles().forEach(detalle -> {
        sb.append(" - ").append(detalle.getProducto().getNombre())
          .append(" | Cantidad: ").append(detalle.getCantidad())
          .append(" | Subtotal: $").append(detalle.getSubtotal()).append("\n");
    });
    sb.append("Total: $").append(venta.getTotal()).append("\n");
    sb.append("Fecha: ").append(venta.getFecha());
    return sb.toString();
    }

    public Factura generarYGuardarFactura(Long nroVenta, String clienteEmail) {
        Venta venta = vr.findById(nroVenta).orElse(null);
        if (venta == null) {
            return null;
        }

        String contenido = crearContenidoFactura(venta);

        Factura factura = new Factura();
        factura.setNroVenta(venta.getId_venta() != null ? venta.getId_venta().intValue() : null);
        factura.setClienteEmail(clienteEmail);
        factura.setContenidoFactura(contenido);
        factura.setFechaEmision(LocalDate.now().toString());

        return fr.save(factura);
    }

    public List<Factura> listarFacturas() {
    return fr.findAll();
    }
}
    
