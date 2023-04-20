package com.asociatialocatari.gestiune.base.repositories;

import com.asociatialocatari.gestiune.base.models.Entman;
import com.asociatialocatari.gestiune.base.models.Lng;
import com.asociatialocatari.gestiune.base.models.MultiLng;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MultiLngRepository extends JpaRepository<MultiLng, Long> {

    Optional<List<MultiLng>> findAllByLngAndEntman(Lng lng, Entman entman);
}
