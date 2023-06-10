package com.asociatialocatari.gal.apart;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ApartMapper {

    @Mapping(source="number", target="number")
    ApartDto toApartDto(Apart apart);

    @Mapping(source="number", target="number")
    Apart toApart(ApartDto apartDto);
}
