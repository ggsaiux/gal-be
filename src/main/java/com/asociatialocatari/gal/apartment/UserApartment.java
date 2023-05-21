package com.asociatialocatari.gal.apartment;

import com.asociatialocatari.gal.base.models.Stt;
import com.asociatialocatari.gal.base.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_apartment", schema = "gal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserApartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_apartment_gen")
    @SequenceGenerator(name = "user_apartment_gen", sequenceName = "gal.user_apartment_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_apartment_user_id_fk"), name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_apartment_apartment_id_fk"), name = "id_apartment")
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_apartment_stt_id_fk"), name = "id_stt")
    private Stt stt;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated;

}
