package com.asociatialocatari.gal.base.repositories;

import com.asociatialocatari.gal.base.models.Lng;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface LngRepository extends JpaRepository<Lng, Long> {

    Optional<Lng> findByAbbrv(String abbrv);
}
