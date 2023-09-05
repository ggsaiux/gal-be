package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.apart.Apart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildStairRepository extends JpaRepository<BuildStair, Long> {

    List<BuildStair> findBuildStairByAsso_Id(Long assoId);

}
