package com.asociatialocatari.gestiune.build_stair;

import com.asociatialocatari.gestiune.apartment.Apartment;
import com.asociatialocatari.gestiune.base.models.Stt;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "build_stair_apartment", schema = "gal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BuildStairApartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "build_stair_apartment_gen")
    @SequenceGenerator(name = "build_stair_apartment_gen", sequenceName = "gal.build_stair_apartment_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "build_stair_apartment_build_stair_id_fk"), name = "id_build_stair")
    private BuildStair buildStair;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "build_stair_apartment_apartment_id_fk"), name = "id_apartment")
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "build_stair_apartment_stt_id_fk"), name = "id_stt")
    private Stt stt;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated;
}
