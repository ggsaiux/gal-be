package com.asociatialocatari.gal.asso;

import com.asociatialocatari.gal.base.Utils;
import com.asociatialocatari.gal.base.exception.GalException;
import com.asociatialocatari.gal.base.models.*;
import com.asociatialocatari.gal.base.repositories.EntmanRepository;
import com.asociatialocatari.gal.base.repositories.LngRepository;
import com.asociatialocatari.gal.base.repositories.MultiLngRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.asociatialocatari.gal.base.exception.ErrorEnum.RUNTIME_ERROR;

@Service
public class UserAssoServiceImpl implements UserAssoService{

    private static final Logger logger = LoggerFactory.getLogger(UserAssoServiceImpl.class);
    private final Utils utils;

    private final LngRepository lngRepository;

    private final MultiLngRepository multiLngRepository;

    private final UserAssoRepository userAssoRepository;

    private final EntmanRepository entmanRepository;

    private final AssoMapper mapper = Mappers.getMapper(AssoMapper.class);

    private final String ASSOS = "assos";

    public UserAssoServiceImpl(Utils utils, LngRepository lngRepository, MultiLngRepository multiLngRepository, UserAssoRepository userAssoRepository, EntmanRepository entmanRepository) {
        this.utils = utils;
        this.lngRepository = lngRepository;
        this.multiLngRepository = multiLngRepository;
        this.userAssoRepository = userAssoRepository;
        this.entmanRepository = entmanRepository;
    }

    //todo comment
    public Map<String,List<AssoDto>> getAssoListByUser() throws GalException {
        //todo logs
        //todo exceptions
        User user = utils.getUserFromContext();
        Map<String,List<AssoDto>> mapAssoDtoList = new HashMap<>();
        List<AssoDto> assoDtoList = new ArrayList<>();
        try {
            List<UserAsso> userAssoList = userAssoRepository.findUserAssosByUser(user);
            for (UserAsso userAsso : userAssoList) {
                assoDtoList.add(mapper.toAssoDto(userAsso.getAsso()));
            }
            mapAssoDtoList.put(ASSOS, assoDtoList);
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on Associations listing!", RUNTIME_ERROR);
        }
        return mapAssoDtoList;
    }

    @Override
    public List<UserAsso> getAllUserAsso() {
        return null;
    }

    @Override
    public void saveUserAsso(UserAsso userAsso) {

    }

    @Override
    public Map<String, UserAsso> getUserAssoById(long id) throws GalException {
        return null;
    }

    @Override
    public void deleteUserAssoById(long id) {

    }

    @Override
    public Page<Asso> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        return null;
    }


 /*   public List<AssoDto> getAssoListByUser(String lngAbbrv) throws GalException {

        //Map<String,List> mapAsso = new HashMap<>();
        List<AntTableColumn> columns = new ArrayList<>();
        User user = utils.getUserFromContext();
        List<AssoDto> assoDtoList = new ArrayList<>();
        try {
            Optional<Entman> entman = entmanRepository.findById(1L); // find entity/table by id need to be constant
            Optional<Lng> lngOpt = lngRepository.findByAbbrv(lngAbbrv); // find language by abbreviation
            Optional<List<MultiLng>> multiLngListOpt = multiLngRepository.findAllByLngAndEntman(lngOpt.get(), entman.get()); // get names in lng
            columns.add(new AntTableColumn("ID", "id","id"));
            *//*
            String name = "Name";
            String description = "Description";
            String address = "Address";*//*
            for (MultiLng multiLng : multiLngListOpt.get()){
                *//*
                if(multiLng.getBase_name().equals("name"))
                    name = multiLng.getLng_name();
                if(multiLng.getBase_name().equals("description"))
                    description = multiLng.getLng_name();
                if(multiLng.getBase_name().equals("address"))
                    address = multiLng.getLng_name();
                 *//*
                columns.add(new AntTableColumn(multiLng.getLng_name(), multiLng.getBase_name(), multiLng.getBase_name()));
            }
            List<UserAsso> userAssoList = userAssoRepository.findUserAssosByUser(user);
            for (UserAsso userAsso : userAssoList) {
                assoDtoList.add(mapper.toAssoDto(userAsso.getAsso()));
            }
            //mapAsso.put("columns", columns);
            //mapAsso.put("dataSource", assoDtoList);
        } catch(Exception e) {
            throw new GalException(e.getMessage(), RUNTIME_ERROR);
        }
        return assoDtoList;
    }*/

    // asso by user as ant table columns data
//    public Map<String,List> getAssoListByUserAsTable(String lngAbbrv) throws GalException {
//
//        Map<String,List> mapAsso = new HashMap<>();
//        List<AntTableColumn> columns = new ArrayList<>();
//        User user = utils.getUserFromContext();
//        List<AssoDto> assoDtoList = new ArrayList<>();
//        try {
//            Optional<Entman> entman = entmanRepository.findById(1L); // find entity/table by id need to be constant
//            Optional<Lng> lngOpt = lngRepository.findByAbbrv(lngAbbrv); // find language by abbreviation
//            Optional<List<MultiLng>> multiLngListOpt = multiLngRepository.findAllByLngAndEntman(lngOpt.get(), entman.get()); // get names in lng
//            columns.add(new AntTableColumn("ID", "id","id"));
//            /*
//            String name = "Name";
//            String description = "Description";
//            String address = "Address";*/
//            for (MultiLng multiLng : multiLngListOpt.get()){
//                /*
//                if(multiLng.getBase_name().equals("name"))
//                    name = multiLng.getLng_name();
//                if(multiLng.getBase_name().equals("description"))
//                    description = multiLng.getLng_name();
//                if(multiLng.getBase_name().equals("address"))
//                    address = multiLng.getLng_name();
//                 */
//                columns.add(new AntTableColumn(multiLng.getLng_name(), multiLng.getBase_name(), multiLng.getBase_name()));
//            }
//            List<UserAsso> userAssoList = userAssoRepository.findUserAssosByUser(user);
//            for (UserAsso userAsso : userAssoList) {
//                assoDtoList.add(mapper.toAssoDto(userAsso.getAsso()));
//            }
//            mapAsso.put("columns", columns);
//            mapAsso.put("dataSource", assoDtoList);
//        } catch(Exception e) {
//            throw new GalException(e.getMessage(), RUNTIME_ERROR);
//        }
//        return mapAsso;
//    }
}
