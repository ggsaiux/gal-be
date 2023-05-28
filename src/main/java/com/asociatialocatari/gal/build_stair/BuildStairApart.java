package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.apart.Apart;
import com.asociatialocatari.gal.base.models.Stt;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "build_stair_apart", schema = "gal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BuildStairApart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "build_stair_apart_gen")
    @SequenceGenerator(name = "build_stair_apart_gen", sequenceName = "gal.build_stair_apart_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "build_stair_apart_build_stair_id_fk"), name = "id_build_stair")
    private BuildStair buildStair;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "build_stair_apart_apart_id_fk"), name = "id_apart")
    private Apart apart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "build_stair_apart_stt_id_fk"), name = "id_stt")
    private Stt stt;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated;
}
