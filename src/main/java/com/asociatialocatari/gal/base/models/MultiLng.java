package com.asociatialocatari.gal.base.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "multi_lng", schema = "gal")
public class MultiLng {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "multi_lng_gen")
    @SequenceGenerator(name = "multi_lng_gen", sequenceName = "gal.multi_lng_id_seq")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 15)
    private String base_name;

    @NotBlank
    @Size(min = 3, max = 15)
    private String lng_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "multi_lng_lng_id_fk"), name = "id_lng")
    private Lng lng;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "multi_lng_entman_id_fk"), name = "id_entman")
    private Entman entman;

}
