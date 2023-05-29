package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.asso.Asso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssoBuildStairRepository extends JpaRepository<AssoBuildStair, Long>{

    List<AssoBuildStair> findAssoBuildStairByAsso(Asso asso);

    Integer deleteAssoBuildStairByBuildStair(BuildStair buildStair);

}