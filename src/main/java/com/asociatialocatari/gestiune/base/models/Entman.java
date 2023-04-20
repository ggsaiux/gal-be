package com.asociatialocatari.gestiune.base.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "entman", schema = "gal")
public class Entman {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "entman_gen")
    @SequenceGenerator(name = "entman_gen", sequenceName = "gal.entman_id_seq")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 15)
    private String table_name;
}
