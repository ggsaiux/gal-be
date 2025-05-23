package com.asociatialocatari.gal.base.repositories;

import com.asociatialocatari.gal.base.models.Stt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SttRepository extends JpaRepository<Stt, Long> {

    Optional<Stt> findByName(String name);
}
