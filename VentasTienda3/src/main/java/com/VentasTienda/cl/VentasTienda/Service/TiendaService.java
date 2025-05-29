package com.VentasTienda.cl.VentasTienda.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VentasTienda.cl.VentasTienda.Model.Tienda;
import com.VentasTienda.cl.VentasTienda.Model.TiendaTrabajador;
import com.VentasTienda.cl.VentasTienda.Model.Trabajador;
import com.VentasTienda.cl.VentasTienda.Repository.TiendaRepository;
import com.VentasTienda.cl.VentasTienda.Repository.TiendaTrabajadorRepository;
import com.VentasTienda.cl.VentasTienda.Repository.TrabajadorRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class TiendaService {
    @Autowired
    private TiendaRepository tr;
    @Autowired
    private TrabajadorRepository tbr;
    @Autowired
    private TiendaTrabajadorRepository ttr;
    
    public Tienda crearTienda(Tienda nuevatienda) {
    nuevatienda.setTrabajadores(null);
    return tr.save(nuevatienda);
    }

    public List<Tienda> listarTiendas() {
        return tr.findAll();
    }
    public Tienda actualizarTienda(Long id, String nuevaDireccion, String nuevoHorario) {
        Tienda tienda = tr.findById(id).orElse(null);
        if(tienda != null) {
            tienda.setDireccion(nuevaDireccion);
            tienda.setHorario_atencion(nuevoHorario);
            return tr.save(tienda);
        }
        return null;
    }
    public String agregarTrabajador(Long tiendaId, Trabajador nuevoTrabajador) {
    Tienda tienda = tr.findById(tiendaId).orElse(null);

        if (tienda != null) {
            tbr.save(nuevoTrabajador);
            TiendaTrabajador tt = new TiendaTrabajador();
            tt.setTienda(tienda);
            tt.setTrabajador(nuevoTrabajador);
            ttr.save(tt);
            return "Trabajador agregado a la tienda";
        } else {
            return "tienda no encontrada";
        }
    }
    public List<Trabajador> listarTrabajadores(Long tiendaId) {
    Tienda tienda = tr.findById(tiendaId).orElse(null);
    if (tienda != null) {
        List<TiendaTrabajador> relaciones = ttr.findByTienda(tienda);
        return relaciones.stream()
                .map(TiendaTrabajador::getTrabajador)
                .toList();
        }
    return null;
    }
    public String eliminarTrabajador(Long tiendaId, Long runTrabajador) {
    Tienda tienda = tr.findById(tiendaId).orElse(null);
    Trabajador trabajador = tbr.findById(runTrabajador).orElse(null);

    if (tienda != null && trabajador != null) {
        TiendaTrabajador relacion = ttr.findByTiendaAndTrabajador(tienda, trabajador);
        if (relacion != null) {
            ttr.delete(relacion);
            return "Trabajador Eliminado";
            }
        }
    return "Tienda o trabajador no encontrado";
    }
    public Tienda actualizarPolitica(Long id, String politicas) {
            Tienda tienda = tr.findById(id).orElse(null);
            if(tienda != null){
                tienda.setPolitica(politicas);
                return tr.save(tienda);
            }
            return null;
        }

}
