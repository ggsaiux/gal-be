package com.asociatialocatari.gestiune.base.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "type", schema = "gal")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "type_gen")
    @SequenceGenerator(name = "type_gen", sequenceName = "gal.type_id_seq")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 15)
    private String name;

}
