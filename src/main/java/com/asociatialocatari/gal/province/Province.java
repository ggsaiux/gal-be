package com.asociatialocatari.gal.province;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "province", schema = "gal")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "province_id_seq")
    @SequenceGenerator(sequenceName = "gal.province_id_seq", allocationSize = 1, name = "province_id_seq")
    private Long id;

    @Column
    @Size(min=2, max=30)
    private String name;

    @Column
    @Size(min=2, max=3)
    private String abbrv;

}
