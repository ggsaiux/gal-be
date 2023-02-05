package com.asociatialocatari.gestiune.base.repositories;

import com.asociatialocatari.gestiune.base.models.Lng;
import com.asociatialocatari.gestiune.base.models.Stt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SttRepository extends JpaRepository<Stt, Long> {

    Optional<Stt> findByName(String name);
}
