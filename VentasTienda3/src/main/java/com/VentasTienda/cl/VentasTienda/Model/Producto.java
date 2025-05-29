package com.VentasTienda.cl.VentasTienda.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table (name = "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    // Atributos.
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (length = 15)
    private Long id_producto;

    @Column (length = 200, nullable = false)
    private String nombre;

    @Column (length = 15, nullable = false)
    private Integer precio;

    @Column (length = 5, nullable = false)
    private Integer cantidad;

    @Column (length = 100, nullable = true)
    private String material;

    @Column (length = 20, nullable = true)
    private String talla;

    @Column (length = 100, nullable = true)
    private String fecha_vencimiento;

    @Column (nullable = true)
    private boolean refrigerar;

}
