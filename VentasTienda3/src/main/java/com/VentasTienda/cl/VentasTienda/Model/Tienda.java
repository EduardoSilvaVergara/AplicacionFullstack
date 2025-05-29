package com.VentasTienda.cl.VentasTienda.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table (name = "Tienda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tienda {

    // Atributos.
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (length = 15)
    private Long id_tienda;

    @Column (length = 70, nullable = false)
    private String direccion;

    @Column (nullable = false)
    private String horario_atencion;

    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TiendaTrabajador> trabajadores;

    @OneToOne
    @JoinColumn (name = "id_inventario")
    private Inventario inventario;

    @Column(length = 300, nullable = false)
    private String politica;

    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL)
    private List<TiendaDetalle> detalles;
}
