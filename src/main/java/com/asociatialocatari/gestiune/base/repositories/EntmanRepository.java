package com.asociatialocatari.gestiune.base.repositories;

import com.asociatialocatari.gestiune.base.models.Entman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntmanRepository extends JpaRepository<Entman, Long> {
}
