package com.asociatialocatari.gal.district;

import com.asociatialocatari.gal.city.City;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "district", schema = "gal")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_id_seq")
    @SequenceGenerator(sequenceName = "gal.district_id_seq", allocationSize = 1, name = "district_id_seq")
    private Long id;

    @Column
    @Size(min=2, max=30)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "district_city_id_fk"), name = "id_city")
    private City city;


}
