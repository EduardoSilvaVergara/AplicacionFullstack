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
@Table (name = "Cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    // Atributos.
    @Id
    @Column (length = 8)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long run_cliente;

    @Column (nullable = false)
    private char dv;

    @Column (nullable = false)
    private String clave;

    @Column (length = 70, nullable = false)
    private String nombre_completo;

    @Column (length = 50, nullable = false)
    private String mail;

    @Column (length = 200, nullable = true)
    private String direccion;

}  
