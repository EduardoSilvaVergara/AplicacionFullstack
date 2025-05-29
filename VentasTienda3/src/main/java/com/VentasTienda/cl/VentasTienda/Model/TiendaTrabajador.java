package com.VentasTienda.cl.VentasTienda.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiendaTrabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "run_trabajador")
    private Trabajador trabajador;

}