package com.asociatialocatari.gestiune.asso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssoBuildStairRepository extends JpaRepository<AssoBuildStair, Long>{
}