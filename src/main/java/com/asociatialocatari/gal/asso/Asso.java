package com.asociatialocatari.gal.asso;

import java.time.LocalDateTime;

import com.asociatialocatari.gal.district.City;
import com.asociatialocatari.gal.district.District;
import com.asociatialocatari.gal.district.Province;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.asociatialocatari.gal.base.models.Stt;

@Data
@Entity
@Table(name = "asso", schema = "gal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Asso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asso_id_seq")
    @SequenceGenerator(sequenceName = "gal.asso_id_seq", allocationSize=1, name = "asso_id_seq")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(columnDefinition = "name")
    private String name;

    @Size(max = 100)
    @Column(name = "description")
    private String description;

    @NotBlank
    @Size(max = 150)
    @Column(name = "address")
    private String address;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "aso_stt_id_fk"), name = "id_stt")
    private Stt stt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "asso_province_id_fk"), name = "id_province", referencedColumnName = "id")
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "asso_city_id_fk"), name = "id_city", referencedColumnName = "id")
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "asso_district_id_fk"), name = "id_district", referencedColumnName = "id")
    private District district;
}
