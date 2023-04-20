package com.asociatialocatari.gestiune.asso;

import org.mapstruct.Mapper;

@Mapper
public interface AssoMapper {
    AssoDto toAssoDto(Asso asso);
    Asso toAsso(AssoDto assoDto);
}
