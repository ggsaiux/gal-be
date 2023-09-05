package com.asociatialocatari.gal.asso;

import com.asociatialocatari.gal.base.Utils;
import com.asociatialocatari.gal.base.exception.ErrorEnum;
import com.asociatialocatari.gal.base.exception.GalException;
import com.asociatialocatari.gal.base.models.Stt;
import com.asociatialocatari.gal.base.models.User;
import com.asociatialocatari.gal.base.repositories.SttRepository;
import com.asociatialocatari.gal.district.CityRepository;
import com.asociatialocatari.gal.district.DistrictRepository;
import com.asociatialocatari.gal.district.ProvinceRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Association Service
 */
@Service
public class AssoService {
    //todo logs
    //todo exceptions

    private static final Logger logger = LoggerFactory.getLogger(AssoService.class);

    private final AssoMapper mapper = Mappers.getMapper(AssoMapper.class);

    private final String ASSO = "asso";

    private final Utils utils;

    private final AssoRepository assoRepository;

    private final ProvinceRepository provinceRepository;

    private final CityRepository cityRepository;

    private final DistrictRepository districtRepository;

    private final SttRepository sttRepository;

    private final UserAssoRepository userAssoRepository;

    public AssoService(
            Utils utils,
            AssoRepository assoRepository,
            ProvinceRepository provinceRepository,
            CityRepository cityRepository,
            DistrictRepository districtRepository,
            SttRepository sttRepository,
            UserAssoRepository userAssoRepository) {
        this.utils = utils;
        this.assoRepository = assoRepository;
        this.provinceRepository = provinceRepository;
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.sttRepository = sttRepository;
        this.userAssoRepository = userAssoRepository;
    }

    //todo for ADMINS
    public Map<String, List<AssoOutDto>> getAllAsso() {
        return null;
    }

    /**
     * Get Association by ID
     *
     * @param id
     * @return
     * @throws GalException
     */
    public Map<String, AssoOutDto> getAssoById(long id) throws GalException {
        //todo logs
        //todo exceptions

        // User user = utils.getUserFromContext();
        //Set<String> roles = utils.getAuthoritiesFromContext();
        Map<String, AssoOutDto> mapAssoDto = new HashMap<>();
        try{
            Optional<Asso> assoOpt = assoRepository.findById(id);
            if(assoOpt.isPresent())
                mapAssoDto.put(ASSO, mapper.toAssoOutDto(assoOpt.get()));
            else {
                logger.error("No association found!");
                throw new GalException("No association found!", ErrorEnum.RUNTIME_ERROR);
            }
        } catch( Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on Association finding!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapAssoDto;
    }

    /**
     * Add or Update Association
     *
     * @param assoInDto
     * @return
     * @throws GalException
     */
    @Transactional
    public Map<String, AssoOutDto> saveAsso(AssoInDto assoInDto) throws GalException {
        //todo logs
        //todo exceptions

        Map<String, AssoOutDto> mapAssoOutDto = new HashMap<>();
        try {
            if(assoInDto != null) {
                User user = utils.getUserFromContext();
                Asso asso = new Asso();
                if(assoInDto.getId() != null){
                    //update
                    Optional<Asso> assoOpt = assoRepository.findById(assoInDto.getId());
                    if (assoOpt.isPresent()) {
                        asso = assoOpt.get();
                        //asso = mapper.toAsso(assoInDto);
                        if (assoInDto.getName() != null)
                            asso.setName(assoInDto.getName());
                        if (assoInDto.getProvince() != 0)
                            asso.setProvince(provinceRepository.findById(assoInDto.getProvince()).get());
                        if (assoInDto.getCity() != 0)
                            asso.setCity(cityRepository.findById(assoInDto.getCity()).get());
                        if (assoInDto.getDistrict() != 0)
                            asso.setDistrict(districtRepository.findById(assoInDto.getDistrict()).get());
                        else
                            asso.setDistrict(null);
                        if (assoInDto.getDescription() != null)
                            asso.setDescription(assoInDto.getDescription());
                        if (assoInDto.getAddress() != null)
                            asso.setAddress(assoInDto.getAddress());
                        assoRepository.save(asso);
                    } else {
                        logger.error("Error on Association saving!");
                        throw new GalException("Error on Association saving!", ErrorEnum.RUNTIME_ERROR);
                    }
                } else {
                    //add new
                    Optional<Stt> sttActiveOpt = sttRepository.findById(1l);
                    asso = mapper.toAsso(assoInDto);
                    asso.setStt(sttActiveOpt.get()); //set state active
                    assoRepository.save(asso);
                    userAssoRepository.save(new UserAsso(user, asso, sttActiveOpt.get()));
                }
                mapAssoOutDto.put(ASSO, mapper.toAssoOutDto(asso));
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on Association saving!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapAssoOutDto;
    }

    public Page<Asso> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        return null;
    }
}
