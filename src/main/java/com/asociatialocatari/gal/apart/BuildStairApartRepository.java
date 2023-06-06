package com.asociatialocatari.gal.apart;

import com.asociatialocatari.gal.build_stair.BuildStair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildStairApartRepository extends JpaRepository<BuildStairApart, Long> {
    List<BuildStairApart> findBuildStairApartByBuildStair(BuildStair buildStair);

    Integer deleteBuildStairApartByApart(Apart apart);
}
