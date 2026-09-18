package com.rollerspeed.rollerspeed1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_flor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoFlor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String color;

    private String descripcion;
}
