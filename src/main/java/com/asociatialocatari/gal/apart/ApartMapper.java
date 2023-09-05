package com.asociatialocatari.gal.apart;

import org.mapstruct.*;

/**
 * Apartment Mapper
 */
@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface ApartMapper {

    @Mapping(source="number", target="number")
    @Mapping(source="buildStair.id", target="idBuildStair")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ApartDto toApartDto(Apart apart);

    @Mapping(source="number", target="number")
    @Mapping(source="idBuildStair", target="buildStair.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Apart toApart(ApartDto apartDto);
}
