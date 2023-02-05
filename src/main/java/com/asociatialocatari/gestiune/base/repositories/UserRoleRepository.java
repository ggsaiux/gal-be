package com.asociatialocatari.gestiune.base.repositories;

import com.asociatialocatari.gestiune.base.models.Role;
import com.asociatialocatari.gestiune.base.models.User;
import com.asociatialocatari.gestiune.base.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Set<Role> findRolesByUser(User user);
}
