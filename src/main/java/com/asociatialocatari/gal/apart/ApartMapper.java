package com.asociatialocatari.gal.apart;

import org.mapstruct.Mapper;

@Mapper
public interface ApartMapper {

    ApartDto toApartDto(Apart apart);
    Apart toApart(ApartDto apartDto);
}
