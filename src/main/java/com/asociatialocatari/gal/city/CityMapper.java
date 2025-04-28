package com.asociatialocatari.gal.city;

import org.mapstruct.Mapper;

@Mapper
public interface CityMapper {

    CityDto toCityDto(City city);

    City toCity(CityDto cityDto);
}
