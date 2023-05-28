package com.asociatialocatari.gal.build_stair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildStairApartRepository extends JpaRepository<BuildStairApart, Long> {
}
