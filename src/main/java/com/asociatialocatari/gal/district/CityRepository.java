package com.asociatialocatari.gal.district;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityById(Long id);

    Optional<List<City>> findCitiesByProvince_IdOrderByName(long id);
}
