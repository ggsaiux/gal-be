package com.asociatialocatari.gal.province;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface ProvinceMapper {

    ProvinceDto toProvinceDto(Province province);

    Province toProvince(ProvinceDto provinceDto);

}
