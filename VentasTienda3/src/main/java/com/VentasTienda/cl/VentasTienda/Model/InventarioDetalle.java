package com.VentasTienda.cl.VentasTienda.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_InventarioDetalle;

    @ManyToOne
    private Inventario inventario;

    @ManyToOne
    private Producto producto;

    private int cantidad;
}
