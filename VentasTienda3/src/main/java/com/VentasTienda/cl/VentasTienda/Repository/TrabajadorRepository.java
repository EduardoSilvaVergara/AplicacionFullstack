package com.VentasTienda.cl.VentasTienda.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.VentasTienda.cl.VentasTienda.Model.Trabajador;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long>{
}
