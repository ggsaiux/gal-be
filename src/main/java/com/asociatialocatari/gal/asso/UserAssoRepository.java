package com.asociatialocatari.gal.asso;

import com.asociatialocatari.gal.base.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAssoRepository extends JpaRepository<UserAsso, Long> {

    List<UserAsso> findUserAssosByUser(User user);
    UserAsso findUserAssosByUserAndAsso(User user, Asso asso);

    Integer deleteUserAssoByUserAndAsso(User user, Asso asso);
}
