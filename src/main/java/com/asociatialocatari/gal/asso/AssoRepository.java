package com.asociatialocatari.gal.asso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssoRepository extends JpaRepository<Asso, Long> {
}
