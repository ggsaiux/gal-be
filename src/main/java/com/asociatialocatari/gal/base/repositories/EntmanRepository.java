package com.asociatialocatari.gal.base.repositories;

import com.asociatialocatari.gal.base.models.Entman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntmanRepository extends JpaRepository<Entman, Long> {
}
