package com.asociatialocatari.gal.cost;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="cost_type", schema="gal_cost")
public class CostType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cost_type_id_seq")
    @SequenceGenerator(sequenceName = "gal_cost.cost_type_id_seq", allocationSize=1, name = "cost_type_id_seq")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(columnDefinition = "name")
    private String name;
}
