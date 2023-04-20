package com.asociatialocatari.gestiune.asso;

import com.asociatialocatari.gestiune.base.Utils;
import com.asociatialocatari.gestiune.base.exception.GalException;
import com.asociatialocatari.gestiune.base.models.*;
import com.asociatialocatari.gestiune.base.repositories.EntmanRepository;
import com.asociatialocatari.gestiune.base.repositories.LngRepository;
import com.asociatialocatari.gestiune.base.repositories.MultiLngRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.asociatialocatari.gestiune.base.exception.ErrorEnum.RUNTIME_ERROR;

@Service
public class UserAssoService {

    private final Utils utils;

    private final LngRepository lngRepository;

    private final MultiLngRepository multiLngRepository;

    private final UserAssoRepository userAssoRepository;

    private final EntmanRepository entmanRepository;

    private AssoMapper mapper = Mappers.getMapper(AssoMapper.class);

    public UserAssoService(Utils utils, LngRepository lngRepository, MultiLngRepository multiLngRepository, UserAssoRepository userAssoRepository, EntmanRepository entmanRepository) {
        this.utils = utils;
        this.lngRepository = lngRepository;
        this.multiLngRepository = multiLngRepository;
        this.userAssoRepository = userAssoRepository;
        this.entmanRepository = entmanRepository;
    }

    public Map<String,List> getAssoListByUser(String lngAbbrv) throws GalException {

        Map<String,List> mapAsso = new HashMap<>();
        List<AntTableColumn> columns = new ArrayList<>();
        User user = utils.getUserFromContext();
        List<AssoDto> assoDtoList = new ArrayList<>();
        try {
            Optional<Entman> entman = entmanRepository.findById(1L); // find entity/table by id need to be constant
            Optional<Lng> lngOpt = lngRepository.findByAbbrv(lngAbbrv); // find language by abbreviation
            Optional<List<MultiLng>> multiLngListOpt = multiLngRepository.findAllByLngAndEntman(lngOpt.get(), entman.get()); // get names in lng
            columns.add(new AntTableColumn("ID", "id","id"));
            String name = "Name";
            String description = "Description";
            String address = "Address";
            for (MultiLng multiLng : multiLngListOpt.get()){
                if(multiLng.getBase_name().equals("name"))
                    name = multiLng.getLng_name();
                if(multiLng.getBase_name().equals("description"))
                    description = multiLng.getLng_name();
                if(multiLng.getBase_name().equals("address"))
                    address = multiLng.getLng_name();
                columns.add(new AntTableColumn(multiLng.getLng_name(), multiLng.getBase_name(), multiLng.getBase_name()));
            }
            List<UserAsso> userAssoList = userAssoRepository.findUserAssosByUser(user);
            for (UserAsso userAsso : userAssoList) {
                assoDtoList.add(mapper.toAssoDto(userAsso.getAsso()));
            }
            mapAsso.put("columns", columns);
            mapAsso.put("dataSource", assoDtoList);
        } catch(Exception e) {
            throw new GalException(e.getMessage(), RUNTIME_ERROR);
        }
        return mapAsso;
    }
}
