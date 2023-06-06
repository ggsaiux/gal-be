package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.asso.Asso;
import com.asociatialocatari.gal.base.models.Stt;
import com.asociatialocatari.gal.build_stair.BuildStair;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "asso_build_stair", schema = "gal")
public class AssoBuildStair {

    public AssoBuildStair() {
    }

    public AssoBuildStair(Asso asso, BuildStair buildStair, Stt stt){
        this.asso = asso;
        this.buildStair = buildStair;
        this.stt = stt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "asso_build_stair_gen")
    @SequenceGenerator(name = "asso_build_stair_gen", sequenceName = "gal.asso_build_stair_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "asso_build_stair_asso_id_fk"), name = "id_asso")
    private Asso asso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "asso_build_stair_build_stair_id_fk"), name = "id_build_stair")
    private BuildStair buildStair;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "asso_build_stair_stt_id_fk"), name = "id_stt")
    private Stt stt;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated;
}
