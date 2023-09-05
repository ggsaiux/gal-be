package com.asociatialocatari.gal.cost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostAssoRepository extends JpaRepository<CostAsso, Long> {
}
