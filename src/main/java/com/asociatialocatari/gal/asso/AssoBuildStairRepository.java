package com.asociatialocatari.gal.asso;

import com.asociatialocatari.gal.build_stair.BuildStair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssoBuildStairRepository extends JpaRepository<AssoBuildStair, Long>{
    Integer deleteAssoBuildStairByBuildStair(BuildStair buildStair);
}