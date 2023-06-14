package com.asociatialocatari.gal.district;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Optional<Province> findProvinceById(Long id);
}
