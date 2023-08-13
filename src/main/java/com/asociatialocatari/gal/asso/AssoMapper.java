package com.asociatialocatari.gal.asso;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface AssoMapper {

    @Mapping(source="province.name", target="province")
    @Mapping(source="city.name", target="city")
    @Mapping(source="district.name", target="district")
    AssoOutDto toAssoOutDto(Asso asso);

    @Mapping(source="province", target="province.id")
    @Mapping(source="city", target="city.id")
    @Mapping(source="district", target="district.id")
    Asso toAsso(AssoInDto assoInDto);
}
