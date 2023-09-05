package com.asociatialocatari.gal.cost;

import com.asociatialocatari.gal.build_stair.BuildStair;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Building by Stair Cost
 */
@Data
@Entity
@Table(name = "cost_build_stair", schema = "gal_cost")
public class CostBuildStair {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cost_build_stair_id_seq")
    @SequenceGenerator(sequenceName = "gal_cost.cost_build_stair_id_seq", allocationSize=1, name = "cost_build_stair_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "cost_build_stair_build_stair_id_fk"), name = "id_build_stair", referencedColumnName = "id")
    private BuildStair buildStair;

    @NotBlank
    @Size(min = 2, max = 2)
    @Column(columnDefinition = "month")
    private String month;

    @NotBlank
    @Size(min = 4, max = 4)
    @Column(columnDefinition = "year")
    private String year;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "cost_asso_cost_type_id_fk"), name = "id_cost_type", referencedColumnName = "id")
    private CostType costType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "cost_asso_cost_repartition_id_fk"), name = "id_cost_repartition", referencedColumnName = "id")
    private CostRepartition costRepartition;

    @Column(columnDefinition = "cost", precision = 10, scale = 2)
    private Double cost;
}
