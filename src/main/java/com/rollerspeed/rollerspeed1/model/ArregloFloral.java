package com.rollerspeed.rollerspeed1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "arreglo_floral")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArregloFloral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "tipo_flor_id")
    private TipoFlor tipoFlor;
}
