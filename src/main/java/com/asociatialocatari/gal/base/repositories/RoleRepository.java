package com.asociatialocatari.gal.base.repositories;

import java.util.Optional;
import com.asociatialocatari.gal.base.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
