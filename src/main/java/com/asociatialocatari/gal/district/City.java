package com.asociatialocatari.gal.district;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "city", schema = "gal")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_seq")
    @SequenceGenerator(sequenceName = "gal.city_id_seq", allocationSize = 1, name = "city_id_seq")
    private Long id;

    @Column
    @Size(min=2, max=30)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "city_province_id_fk"), name = "id_province")
    private Province province;
}
