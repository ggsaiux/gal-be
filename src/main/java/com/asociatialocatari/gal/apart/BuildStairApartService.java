package com.asociatialocatari.gal.apart;

import com.asociatialocatari.gal.asso.Asso;
import com.asociatialocatari.gal.asso.AssoRepository;
import com.asociatialocatari.gal.base.exception.GalException;
import com.asociatialocatari.gal.build_stair.*;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.asociatialocatari.gal.base.exception.ErrorEnum.RUNTIME_ERROR;

@Service
public class BuildStairApartService {

    private static final Logger logger = LoggerFactory.getLogger(BuildStairApartService.class);

    private final BuildStairRepository buildStairRepository;

    private final BuildStairApartRepository buildStairApartRepository;

    private final ApartMapper mapper = Mappers.getMapper(ApartMapper.class);

    //BSS = Builds Stairs
    private final String APARTS = "aparts";

    public BuildStairApartService(BuildStairRepository buildStairRepository, BuildStairApartRepository buildStairApartRepository) {
        this.buildStairRepository = buildStairRepository;
        this.buildStairApartRepository = buildStairApartRepository;
    }

    public Map<String, List<ApartDto>> getApartsByBuildStair(long id) throws GalException {
        //todo logs
        //todo exceptions
        Optional<BuildStair> buildStairOpt = buildStairRepository.findById(id);

        if(!buildStairOpt.isPresent())
            throw new GalException("Error on apartments listing!", RUNTIME_ERROR);

        Map<String,List<ApartDto>> mapApartDtoList = new HashMap<>();
        List<ApartDto> apartDtoList = new ArrayList<>();
        try {
            List<BuildStairApart> buildStairApartList = buildStairApartRepository.findBuildStairApartByBuildStair(buildStairOpt.get());
            for (BuildStairApart buildStairApart : buildStairApartList) {
                apartDtoList.add(mapper.toApartDto(buildStairApart.getApart()));
            }
            mapApartDtoList.put(APARTS, apartDtoList);
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on apartments listing!", RUNTIME_ERROR);
        }
        return mapApartDtoList;
    }

}
