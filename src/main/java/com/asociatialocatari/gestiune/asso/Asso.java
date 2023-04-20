package com.asociatialocatari.gestiune.asso;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.asociatialocatari.gestiune.base.models.Stt;

@Data
@Entity
@Table(name = "asso", schema = "gal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Asso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "asso_gen")
    @SequenceGenerator(name = "asso_gen", sequenceName = "gal.asso_id_seq")
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
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "aso_stt_id_fk"), name = "id_stt")
    private Stt stt;
}
