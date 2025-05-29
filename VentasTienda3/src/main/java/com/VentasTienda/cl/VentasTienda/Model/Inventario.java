package com.VentasTienda.cl.VentasTienda.Model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table (name = "Inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    // Atributos.
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (length = 10)
    private Long id_inventario;

    @OneToOne
    @JoinColumn (name = "administrador_run")
    private Especialista_almacen administrador;

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL)
    private List<InventarioDetalle> detalles;

    @OneToOne(mappedBy = "inventario")
    private Tienda tienda;
}
