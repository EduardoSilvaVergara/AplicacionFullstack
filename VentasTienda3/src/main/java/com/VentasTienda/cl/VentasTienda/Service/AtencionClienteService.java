package com.VentasTienda.cl.VentasTienda.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VentasTienda.cl.VentasTienda.Model.Devolucion;
import com.VentasTienda.cl.VentasTienda.Model.Producto;
import com.VentasTienda.cl.VentasTienda.Model.Reclamo;
import com.VentasTienda.cl.VentasTienda.Repository.DevolucionRepository;
import com.VentasTienda.cl.VentasTienda.Repository.ProductoRepository;
import com.VentasTienda.cl.VentasTienda.Repository.ReclamoRepository;

@Service
public class AtencionClienteService {

    @Autowired
    private DevolucionRepository dr;

    @Autowired
    private ReclamoRepository rr;

    @Autowired
    private ProductoRepository pr;

    public Devolucion registrarDevolucion(Long id, int cantidad, String motivo) {
        Producto producto = pr.findById(id).orElse(null);
        if (producto == null || cantidad <= 0) return null;

        Devolucion devolucion = new Devolucion();
        devolucion.setProducto(producto);
        devolucion.setCantidad(cantidad);
        devolucion.setMotivo(motivo);
        devolucion.setFecha(LocalDate.now().toString());

        return dr.save(devolucion);
    }

    public Reclamo registrarReclamo(String detalle) {
        Reclamo reclamacion = new Reclamo();
        reclamacion.setDetalle(detalle);
        reclamacion.setFecha(LocalDate.now().toString());
        reclamacion.setEstado("Pendiente");

        return rr.save(reclamacion);
    }   

    public List<Reclamo> listarReclamo() {
        return rr.findAll();
    }

    public Reclamo actualizarEstadoReclamo(Long id, String nuevoEstado) {
        Reclamo reclamacion = rr.findById(id).orElse(null);

        if (reclamacion != null) {
            reclamacion.setEstado(nuevoEstado);
            return rr.save(reclamacion);
        }
        return null;
    }











}
