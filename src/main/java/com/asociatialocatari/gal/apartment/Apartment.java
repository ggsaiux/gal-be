package com.asociatialocatari.gal.apartment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "apartment", schema = "gal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartment_id_seq")
    @SequenceGenerator(sequenceName = "gal.apartment_id_seq", allocationSize = 1, name = "apartment_id_seq")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "tenants_number")
    private Integer tenantsNumber;

    @Column(name = "m2")
    private Double m2;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated;

}
