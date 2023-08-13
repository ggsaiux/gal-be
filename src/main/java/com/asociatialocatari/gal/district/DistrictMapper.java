package com.asociatialocatari.gal.district;

import org.mapstruct.Mapper;

@Mapper
public interface DistrictMapper {

    DistrictDto toDistrictDto(District district);

    District toDistrict(DistrictDto districtDto);
}
