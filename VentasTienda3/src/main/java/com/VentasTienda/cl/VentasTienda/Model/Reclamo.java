package com.VentasTienda.cl.VentasTienda.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reclamo;

    @Column(nullable = false)
    private String detalle;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private String estado;

    @OneToOne
    private Cliente cliente;
}