package com.asociatialocatari.gal.build_stair;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "build_stair", schema = "gal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BuildStair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "build_stair_gen")
    @SequenceGenerator(name = "build_stair_gen", sequenceName = "gal.build_stair_id_seq")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "number")
    private Integer number;

    @NotBlank
    @Size(min = 1, max = 3)
    @Column(name = "stair")
    private String stair;

    @NotBlank
    @Size(max = 150)
    @Column(name = "address")
    private String address;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated;
}
