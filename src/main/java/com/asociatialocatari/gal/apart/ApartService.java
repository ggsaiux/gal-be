package com.asociatialocatari.gal.apart;

import com.asociatialocatari.gal.asso.Asso;
import com.asociatialocatari.gal.asso.AssoRepository;
import com.asociatialocatari.gal.base.exception.ErrorEnum;
import com.asociatialocatari.gal.base.exception.GalException;
import com.asociatialocatari.gal.build_stair.BuildStair;
import com.asociatialocatari.gal.build_stair.BuildStairRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.asociatialocatari.gal.base.exception.ErrorEnum.RUNTIME_ERROR;

/**
 * Apartment Service
 */
@Service
public class ApartService {
    //todo logs
    //todo exceptions

    private static final Logger logger = LoggerFactory.getLogger(ApartService.class);

    private final ApartMapper mapper = Mappers.getMapper(ApartMapper.class);

    private final String APART = "apart";

    private final String APARTS = "aparts";

    private final ApartRepository apartRepository;

    private final BuildStairRepository buildStairRepository;

    private final AssoRepository assoRepository;

    public ApartService(ApartRepository apartRepository, BuildStairRepository buildStairRepository, AssoRepository assoRepository) {
        this.apartRepository = apartRepository;
        this.buildStairRepository = buildStairRepository;
        this.assoRepository = assoRepository;
    }

    //todo for ADMINS

    /**
     * List of Apartments by Building Stair
     *
     * @param buildStairId
     * @return
     * @throws GalException
     */
    public Map<String, List<ApartDto>> getApartsByBuildStair(Long buildStairId) throws GalException {
        Optional<BuildStair> buildStairOpt = buildStairRepository.findById(buildStairId);
        if(!buildStairOpt.isPresent())
            throw new GalException("Error on apartments listing!", RUNTIME_ERROR);

        Map<String,List<ApartDto>> mapApartDtoList = new HashMap<>();
        List<ApartDto> apartDtoList = new ArrayList<>();
        try {
            List<Apart> apartList = apartRepository.findApartsByBuildStair_Id(buildStairId);
            for (Apart apart : apartList) {
                apartDtoList.add(mapper.toApartDto(apart));
            }
            mapApartDtoList.put(APARTS, apartDtoList);
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on apartments listing!", RUNTIME_ERROR);
        }
        return mapApartDtoList;
    }

    /**
     * Get Apartment by ID
     *
     * @param id
     * @return
     * @throws GalException
     */
    public Map<String, ApartDto> getApartById(long id) throws GalException {
        Map<String, ApartDto> mapApartDto = new HashMap<>();
        try{
            Optional<Apart> apartOpt = apartRepository.findById(id);
            if(apartOpt.isPresent())
                mapApartDto.put(APART, mapper.toApartDto(apartOpt.get()));
            else {
                logger.error("No apartment found!");
                throw new GalException("No apartment found!", ErrorEnum.RUNTIME_ERROR);
            }
        } catch( Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on apartment finding!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapApartDto;
    }

    /**
     * Add Apartment
     *
     * @param apartDto
     * @return
     * @throws GalException
     */
    public Map<String, ApartDto> addApart(ApartDto apartDto) throws GalException {
        Map<String, ApartDto> mapApartDto = new HashMap<>();
        try {
            if(apartDto != null) {
                Apart apart = new Apart();
                apart = mapper.toApart(apartDto);
                apart.setBuildStair(buildStairRepository.findById(apartDto.getIdBuildStair()).get());
                apartRepository.save(apart);
                mapApartDto.put(APART, mapper.toApartDto(apart));
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on apartment saving!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapApartDto;
    }

    /**
     * Update Apartment
     *
     * @param apartDto
     * @return
     * @throws GalException
     */
    public Map<String, ApartDto> updateApart(ApartDto apartDto) throws GalException {
        Map<String, ApartDto> mapApartDto = new HashMap<>();
        try {
            if(apartDto != null) {
                Apart apart = new Apart();
                Optional<Apart> apartOpt = apartRepository.findById(apartDto.getId());
                if (apartOpt.isPresent()) {
                    apart = apartOpt.get();
                    apart = mapper.toApart(apartDto);
                    apartRepository.save(apart);
                } else {
                    logger.error("Error on apartment saving!");
                    throw new GalException("Error on apartment saving!", ErrorEnum.RUNTIME_ERROR);
                }
                mapApartDto.put(APART, mapper.toApartDto(apart));
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on apartment saving!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapApartDto;
    }
}
