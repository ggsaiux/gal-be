package com.asociatialocatari.gal.base.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "lng", schema = "gal")
public class Lng {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "lng_gen")
    @SequenceGenerator(name = "lng_gen", sequenceName = "gal.lng_id_seq")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 10)
    private String name;

    @NotBlank
    private String abbrv;
}
