package com.asociatialocatari.gestiune.asso;

import com.asociatialocatari.gestiune.base.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAssoRepository extends JpaRepository<UserAsso, Long> {

    List<UserAsso> findUserAssosByUser(User user);
}
