package com.asociatialocatari.gal.build_stair;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BuildStairMapper {

    @Mapping(source="district.name", target="district")
    BuildStairDto toBuildStairDto(BuildStair buildStair);

    @Mapping(source="district", target="district.name")
    BuildStair toBuildStair(BuildStairDto buildStairDto);
}
