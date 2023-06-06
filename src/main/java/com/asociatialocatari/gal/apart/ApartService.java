package com.asociatialocatari.gal.apart;

import com.asociatialocatari.gal.asso.Asso;
import com.asociatialocatari.gal.base.exception.ErrorEnum;
import com.asociatialocatari.gal.base.exception.GalException;
import com.asociatialocatari.gal.base.models.Stt;
import com.asociatialocatari.gal.base.repositories.SttRepository;
import com.asociatialocatari.gal.build_stair.*;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApartService {

    private static final Logger logger = LoggerFactory.getLogger(ApartService.class);

    private final ApartMapper mapper = Mappers.getMapper(ApartMapper.class);

    private final String APART = "apart";

    private final ApartRepository apartRepository;

    private final BuildStairRepository buildStairRepository;

    private final BuildStairApartRepository buildStairApartRepository;

    private final SttRepository sttRepository;


    public ApartService(ApartRepository apartRepository, BuildStairRepository buildStairRepository, BuildStairApartRepository buildStairApartRepository, SttRepository sttRepository) {
        this.apartRepository = apartRepository;
        this.buildStairRepository = buildStairRepository;
        this.buildStairApartRepository = buildStairApartRepository;
        this.sttRepository = sttRepository;
    }

    //todo for ADMINS
    public Map<String, List<ApartDto>> getAllAparts() {
        return null;
    }

    public Map<String, ApartDto> getApartById(long id) throws GalException {

        //todo logs
        //todo exceptions
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

    public Map<String, ApartDto> saveApart(ApartDto apartDto, long buildStairId) throws GalException {
        //todo
        Map<String, ApartDto> mapApartDto = new HashMap<>();
        try {
            if(apartDto != null) {
                Apart apart = new Apart();
                if(apartDto.getId() != null){
                    Optional<Apart> apartOpt = apartRepository.findById(apartDto.getId());
                    if (apartOpt.isPresent()) {
                        apart = apartOpt.get();
                        apart = mapper.toApart(apartDto);
                        apartRepository.save(apart);
                    } else {
                        logger.error("Error on apartment saving!");
                        throw new GalException("Error on apartment saving!", ErrorEnum.RUNTIME_ERROR);
                    }
                } else {
                    Optional<BuildStair> buildStairOpt = buildStairRepository.findById(buildStairId);
                    if(buildStairOpt.isPresent()) {
                        Optional<Stt> sttActiveOpt = sttRepository.findById(1l);
                        apart = mapper.toApart(apartDto);
                        //apart.setStt(sttActiveOpt.get()); //set state active
                        apartRepository.save(apart);
                        buildStairApartRepository.save(new BuildStairApart(buildStairOpt.get(), apart, sttActiveOpt.get()));
                    }
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
