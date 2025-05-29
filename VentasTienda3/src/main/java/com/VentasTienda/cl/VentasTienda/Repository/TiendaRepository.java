package com.VentasTienda.cl.VentasTienda.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.VentasTienda.cl.VentasTienda.Model.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long>{
}
