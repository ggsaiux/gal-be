package com.asociatialocatari.gal.district;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    Optional<District> findDistrictByName(String districtName);

    Optional<District> findDistrictById(Long id);
}
