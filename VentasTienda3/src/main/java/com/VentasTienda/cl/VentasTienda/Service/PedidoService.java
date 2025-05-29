package com.VentasTienda.cl.VentasTienda.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VentasTienda.cl.VentasTienda.Model.Pedido;
import com.VentasTienda.cl.VentasTienda.Model.PedidoDetalle;
import com.VentasTienda.cl.VentasTienda.Model.Producto;
import com.VentasTienda.cl.VentasTienda.Model.Proveedor;
import com.VentasTienda.cl.VentasTienda.Model.Tienda;
import com.VentasTienda.cl.VentasTienda.Repository.PedidoRepository;
import com.VentasTienda.cl.VentasTienda.Repository.ProductoRepository;
import com.VentasTienda.cl.VentasTienda.Repository.ProveedorRepository;
import com.VentasTienda.cl.VentasTienda.Repository.TiendaRepository;


@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pdr;
    @Autowired
    private ProductoRepository pr;
    @Autowired
    private TiendaRepository tr;
    @Autowired
    private ProveedorRepository pvr;

    public Pedido crearPedido(Long tiendaId, Long proveedorId, List<Long> productoIds, List<Integer> cantidades) {
    Tienda tienda = tr.findById(tiendaId).orElse(null);
    Proveedor proveedor = pvr.findById(proveedorId).orElse(null);
    if (tienda == null || proveedor == null || productoIds == null || cantidades == null || productoIds.size() != cantidades.size()) return null;
    Pedido pedido = new Pedido();
    pedido.setTienda(tienda);
    pedido.setAutorizado(false);
    pedido.setFechaPedido(LocalDate.now().toString());
    pedido.setProveedor(proveedor);
    List<PedidoDetalle> detalles = new ArrayList<>();
    for (int i = 0; i < productoIds.size(); i++) {
        Producto producto = pr.findById(productoIds.get(i)).orElse(null);
        // Solo validar que el producto exista
        if (producto == null) continue;
        PedidoDetalle detalle = new PedidoDetalle();
        detalle.setProducto(producto);
        detalle.setCantidad(cantidades.get(i));
        detalles.add(detalle);
    }
    pedido.setDetalles(detalles);

    return pdr.save(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return pdr.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pdr.findById(id).orElse(null);
    }

    public Pedido autorizarPedido(Long id) {
        Pedido pedido = pdr.findById(id).orElse(null);
        if(pedido != null) {
            pedido.setAutorizado(true);
            return pdr.save(pedido);
        }
        return null;
    }

    public boolean eliminarPedido(Long id) {
        if(pr.existsById(id)) {
            pr.deleteById(id);
            return true;
        }
        return false;
    }

    public Pedido actualizarPedido(Long id, Pedido nuevoPedido) {
    Pedido pedido = pdr.findById(id).orElse(null);
    if (pedido != null) {
        pedido.setDetalles(nuevoPedido.getDetalles());
        pedido.setFechaPedido(nuevoPedido.getFechaPedido());
        pedido.setAutorizado(nuevoPedido.isAutorizado());
        pedido.setProveedor(nuevoPedido.getProveedor());
        return pdr.save(pedido);
    }
    return null;
    }   




}
