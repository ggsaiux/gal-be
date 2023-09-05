package com.asociatialocatari.gal.apart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Apartment Repository
 */
@Repository
public interface ApartRepository extends JpaRepository<Apart, Long> {

    List<Apart> findApartsByBuildStair_Id(long buildStairId);
}
