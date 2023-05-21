package com.asociatialocatari.gal.asso;

import com.asociatialocatari.gal.base.exception.GalException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface AssoService {
    Map<String,List<AssoDto>> getAllAsso();
    Map<String,AssoDto> saveAsso(AssoDto assoDto) throws GalException;
    Map<String,AssoDto> getAssoById(long id) throws GalException;
    void deleteAssoById(long id);
    Page<Asso> findPaginated(int pageNum, int pageSize,
                             String sortField,
                             String sortDirection);
}
