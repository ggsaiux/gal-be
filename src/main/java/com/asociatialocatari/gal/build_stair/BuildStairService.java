package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.asso.Asso;
import com.asociatialocatari.gal.asso.AssoRepository;
import com.asociatialocatari.gal.base.exception.ErrorEnum;
import com.asociatialocatari.gal.base.exception.GalException;
import com.asociatialocatari.gal.base.models.Stt;
import com.asociatialocatari.gal.base.repositories.SttRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BuildStairService {

    private static final Logger logger = LoggerFactory.getLogger(BuildStairService.class);

    private final BuildStairMapper mapper = Mappers.getMapper(BuildStairMapper.class);

    //BSS = Builds Stairs
    private final String BSS = "buildsStairs";  // todo singular

    private BuildStairRepository buildStairRepository;

    private final AssoRepository assoRepository;

    private final AssoBuildStairRepository assoBuilStairRepository;

    private final SttRepository sttRepository;

    public BuildStairService(BuildStairRepository buildStairRepository, AssoRepository assoRepository, AssoBuildStairRepository assoBuilStairRepository, SttRepository sttRepository) {
        this.buildStairRepository = buildStairRepository;
        this.assoRepository = assoRepository;
        this.assoBuilStairRepository = assoBuilStairRepository;
        this.sttRepository = sttRepository;
    }

    //todo for ADMINS
    public Map<String, List<BuildStairDto>> getAllBuildsStairs() {
        return null;
    }

    public Map<String, BuildStairDto> getBuildStairById(long id) throws GalException {
        //todo logs
        //todo exceptions

        Map<String, BuildStairDto> mapBuildStairDto = new HashMap<>();
        try{
            Optional<BuildStair> buildStairOpt = buildStairRepository.findById(id);
            if(buildStairOpt.isPresent())
                mapBuildStairDto.put(BSS, mapper.toBuildStairDto(buildStairOpt.get()));
            else {
                logger.error("No build & stair found!");
                throw new GalException("No build & stair found!", ErrorEnum.RUNTIME_ERROR);
            }
        } catch( Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on Build & Stair finding!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapBuildStairDto;
    }

    public Map<String, BuildStairDto> saveBuildStair(BuildStairDto buildStairDto, long assoId) throws GalException {
        //todo
        Map<String, BuildStairDto> mapBuildStairDto = new HashMap<>();
        try {
            if(buildStairDto != null) {
                BuildStair buildStair = new BuildStair();
                if(buildStairDto.getId() != null){
                    Optional<BuildStair> buildStairOpt = buildStairRepository.findById(buildStairDto.getId());
                    if (buildStairOpt.isPresent()) {
                        buildStair = buildStairOpt.get();
                        buildStair = mapper.toBuildStair(buildStairDto);
                        buildStairRepository.save(buildStair);
                    } else {
                        logger.error("Error on Build & Stair saving!");
                        throw new GalException("Error on Build & Stair saving!", ErrorEnum.RUNTIME_ERROR);
                    }
                } else {
                    Optional<Asso> assoOpt = assoRepository.findById(assoId);
                    if(assoOpt.isPresent()) {
                        Optional<Stt> sttActiveOpt = sttRepository.findById(1l);
                        buildStair = mapper.toBuildStair(buildStairDto);
                        buildStair.setStt(sttActiveOpt.get()); //set state active
                        buildStairRepository.save(buildStair);
                        assoBuilStairRepository.save(new AssoBuildStair(assoOpt.get(), buildStair, sttActiveOpt.get()));
                    }
                }
                mapBuildStairDto.put(BSS, mapper.toBuildStairDto(buildStair));
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on Build & Stair saving!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapBuildStairDto;
    }
}
