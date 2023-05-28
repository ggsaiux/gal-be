package com.asociatialocatari.gal.district;

import com.asociatialocatari.gal.base.models.Stt;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "district", schema = "gal")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_id_seq")
    @SequenceGenerator(sequenceName = "gal.district_id_seq", allocationSize = 1, name = "district_id_seq")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "district_stt_id_fk"), name = "id_stt", referencedColumnName = "id")
    private Stt stt;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated = LocalDateTime.now();
}
