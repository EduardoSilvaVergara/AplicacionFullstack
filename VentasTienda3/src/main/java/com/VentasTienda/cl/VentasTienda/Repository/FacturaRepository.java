package com.VentasTienda.cl.VentasTienda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.VentasTienda.cl.VentasTienda.Model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long>{
}
