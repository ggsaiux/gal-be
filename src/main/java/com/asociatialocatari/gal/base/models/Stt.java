package com.asociatialocatari.gal.base.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "stt", schema = "gal")
public class Stt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "stt_gen")
    @SequenceGenerator(name = "stt_gen", sequenceName = "gal.stt_id_seq")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 15)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "stt_type_type_id_fk"), name = "id_type", referencedColumnName = "id")
    private Type type;

}