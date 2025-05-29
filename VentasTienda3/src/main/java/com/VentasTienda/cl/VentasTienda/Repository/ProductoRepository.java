package com.VentasTienda.cl.VentasTienda.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.VentasTienda.cl.VentasTienda.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
}
