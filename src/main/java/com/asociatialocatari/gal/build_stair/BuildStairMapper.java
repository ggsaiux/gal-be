package com.asociatialocatari.gal.build_stair;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
//@Mapper( nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface BuildStairMapper {

//    @Mapping(source="district.name", target="district")
    BuildStairDto toBuildStairDto(BuildStair buildStair);

//    @Mapping(source="district", target="district.name")
    BuildStair toBuildStair(BuildStairDto buildStairDto);
}
