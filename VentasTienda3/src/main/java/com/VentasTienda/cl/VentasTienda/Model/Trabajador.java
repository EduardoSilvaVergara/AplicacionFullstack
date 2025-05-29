package com.VentasTienda.cl.VentasTienda.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name= "Trabajador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trabajador {

    // Atributos.
    @Id
    @Column (length = 13)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long run_Trabajador;

    @Column (nullable = false)
    private char dv;

    @Column (length = 100, nullable = false)
    private String nombre_completo;

    @Column (length = 15, nullable = false)
    private Long sueldoBase;

    @Column (nullable = false)
    private String horario_de_trabajo;

    @OneToMany(mappedBy = "trabajador", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TiendaTrabajador> tiendas;

    
}
