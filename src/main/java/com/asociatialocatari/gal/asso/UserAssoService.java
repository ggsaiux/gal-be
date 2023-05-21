package com.asociatialocatari.gal.asso;

import com.asociatialocatari.gal.base.exception.GalException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserAssoService {

    Map<String,List<AssoDto>> getAssoListByUser() throws GalException;
    List<UserAsso> getAllUserAsso();
    void saveUserAsso(UserAsso userAsso);
    Map<String,UserAsso> getUserAssoById(long id) throws GalException;
    void deleteUserAssoById(long id);
    Page<Asso> findPaginated(int pageNum, int pageSize,
                             String sortField,
                             String sortDirection);
}
