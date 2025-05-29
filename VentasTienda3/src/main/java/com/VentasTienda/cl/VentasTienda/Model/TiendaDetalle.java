package com.VentasTienda.cl.VentasTienda.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiendaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_TiendaDetalle;

    @ManyToOne
    private Tienda tienda;

    @ManyToOne
    private Producto producto;

    private int stock;
}