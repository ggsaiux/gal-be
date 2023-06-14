package com.asociatialocatari.gal.apart;

import com.asociatialocatari.gal.build_stair.BuildStair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartRepository extends JpaRepository<Apart, Long> {

    List<Apart> findApartsByBuildStair_Id(long buildStairId);
}
