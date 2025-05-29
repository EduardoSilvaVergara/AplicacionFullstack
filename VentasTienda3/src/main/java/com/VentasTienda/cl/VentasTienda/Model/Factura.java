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
@Table(name = "Factura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {


    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_factura;
    
    @Column (nullable = true)
    private Integer nroVenta;
    
    @Column (nullable = true)
    private String clienteEmail;

    @Column (nullable = false)
    private String contenidoFactura;

    @Column (nullable = false)
    private String fechaEmision;

}