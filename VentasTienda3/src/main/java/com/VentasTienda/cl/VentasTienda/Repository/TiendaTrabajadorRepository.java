package com.VentasTienda.cl.VentasTienda.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.VentasTienda.cl.VentasTienda.Model.Tienda;
import com.VentasTienda.cl.VentasTienda.Model.Trabajador;
import com.VentasTienda.cl.VentasTienda.Model.TiendaTrabajador;

public interface TiendaTrabajadorRepository extends JpaRepository<TiendaTrabajador, Long> {
    List<TiendaTrabajador> findByTienda(Tienda tienda);
    TiendaTrabajador findByTiendaAndTrabajador(Tienda tienda, Trabajador trabajador);
}