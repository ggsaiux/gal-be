package com.asociatialocatari.gal.asso;

import com.asociatialocatari.gal.base.models.Stt;
import com.asociatialocatari.gal.base.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_asso", schema = "gal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserAsso {
    public UserAsso() {
    }

    public UserAsso(User user, Asso asso, Stt stt) {
        this.user = user;
        this.asso = asso;
        this.stt = stt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_asso_id_seq")
    @SequenceGenerator(sequenceName = "gal.user_asso_id_seq", allocationSize = 1, name = "user_asso_gid_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_asso_user_id_fk"), name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_asso_asso_id_fk"), name = "id_asso", referencedColumnName = "id")
    private Asso asso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_asso_stt_id_fk"), name = "id_stt", referencedColumnName = "id")
    private Stt stt;

    @Column(name ="inserted", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime inserted = LocalDateTime.now();

    @Column(name ="updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated;
}
