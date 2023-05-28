package com.asociatialocatari.gal.apart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartRepository extends JpaRepository<Apart, Long> {
}
