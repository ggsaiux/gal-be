package com.asociatialocatari.gal.base.repositories;

import com.asociatialocatari.gal.base.models.Entman;
import com.asociatialocatari.gal.base.models.Lng;
import com.asociatialocatari.gal.base.models.MultiLng;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MultiLngRepository extends JpaRepository<MultiLng, Long> {

    Optional<List<MultiLng>> findAllByLngAndEntman(Lng lng, Entman entman);
}
