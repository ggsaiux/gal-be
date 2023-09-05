package com.asociatialocatari.gal.build_stair;

import org.mapstruct.*;

/**
 * Building Stair Mapper
 */
@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface BuildStairMapper {

    @Mapping(source="asso.id", target="idAsso")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    BuildStairDto toBuildStairDto(BuildStair buildStair);

    @Mapping(source="idAsso", target="asso.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    BuildStair toBuildStair(BuildStairDto buildStairDto);
}
