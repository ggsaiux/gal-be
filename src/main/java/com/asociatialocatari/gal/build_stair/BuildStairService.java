package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.asso.Asso;
import com.asociatialocatari.gal.asso.AssoRepository;
import com.asociatialocatari.gal.base.exception.ErrorEnum;
import com.asociatialocatari.gal.base.exception.GalException;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.asociatialocatari.gal.base.exception.ErrorEnum.RUNTIME_ERROR;

/**
 * Building Stair Service
 */
@Service
public class BuildStairService {
    //todo logs
    //todo exceptions

    private static final Logger logger = LoggerFactory.getLogger(BuildStairService.class);

    private static final String BS = "buildStair";

    private static final String BSS = "buildsStairs";

    private final BuildStairMapper mapper = Mappers.getMapper(BuildStairMapper.class);

    private BuildStairRepository buildStairRepository;

    private final AssoRepository assoRepository;

    public BuildStairService(
            BuildStairRepository buildStairRepository,
            AssoRepository assoRepository
    ) {
        this.buildStairRepository = buildStairRepository;
        this.assoRepository = assoRepository;
    }

    //todo for ADMINS
    public Map<String, List<BuildStairDto>> getAllBuildsStairs() {
        return null;
    }

    /**
     * List buildings Stairs by Association
     *
     * @param assoId
     * @return
     * @throws GalException
     */
    public Map<String, List<BuildStairDto>> getBuildsStairsByAsso(Long assoId) throws GalException {

        Optional<Asso> assoOpt = assoRepository.findById(assoId);
        if(!assoOpt.isPresent())
            throw new GalException("Error on buildings stairs listing!", RUNTIME_ERROR);

        Map<String,List<BuildStairDto>> mapBuildStairDtoList = new HashMap<>();
        List<BuildStairDto> buildStairDtoList = new ArrayList<>();
        try {
            List<BuildStair> buildStairList = buildStairRepository.findBuildStairByAsso_Id(assoId);
            for (BuildStair buildStair : buildStairList) {
                buildStairDtoList.add(mapper.toBuildStairDto(buildStair));
            }
            mapBuildStairDtoList.put(BSS, buildStairDtoList);
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on buildings stairs listing!", RUNTIME_ERROR);
        }
        return mapBuildStairDtoList;
    }

    /**
     * Get Building Stair by ID
     *
     * @param id
     * @return
     * @throws GalException
     */
    public Map<String, BuildStairDto> getBuildStairById(long id) throws GalException {
        //todo logs
        //todo exceptions
        Map<String, BuildStairDto> mapBuildStairDto = new HashMap<>();
        try{
            Optional<BuildStair> buildStairOpt = buildStairRepository.findById(id);
            if(buildStairOpt.isPresent())
                mapBuildStairDto.put(BS, mapper.toBuildStairDto(buildStairOpt.get()));
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

    /**
     * Add Building Stair
     *
     * @param buildStairDto
     * @return
     * @throws GalException
     */
    public Map<String, BuildStairDto> addBuildStair(BuildStairDto buildStairDto) throws GalException {
        Map<String, BuildStairDto> mapBuildStairDto = new HashMap<>();
        try {
            if(buildStairDto != null) {
                BuildStair buildStair = new BuildStair();
                buildStair = mapper.toBuildStair(buildStairDto);
                buildStair.setAsso(assoRepository.findById(buildStairDto.getIdAsso()).get());
                buildStairRepository.save(buildStair);
                mapBuildStairDto.put(BS, mapper.toBuildStairDto(buildStair));
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on Build & Stair saving!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapBuildStairDto;
    }

    /**
     * Update Building Stair
     *
     * @param buildStairDto
     * @return
     * @throws GalException
     */
    public Map<String, BuildStairDto> updateBuildStair(BuildStairDto buildStairDto) throws GalException {
        Map<String, BuildStairDto> mapBuildStairDto = new HashMap<>();
        try {
            if(buildStairDto != null) {
                BuildStair buildStair = new BuildStair();
                Optional<BuildStair> buildStairOpt = buildStairRepository.findById(buildStairDto.getId());
                if (buildStairOpt.isPresent()) {
                    buildStair = buildStairOpt.get();
                    buildStair = mapper.toBuildStair(buildStairDto);
                    buildStairRepository.save(buildStair);
                } else {
                    logger.error("Error on Building Stair saving!");
                    throw new GalException("Error on Building Stair saving!", ErrorEnum.RUNTIME_ERROR);
                }
                mapBuildStairDto.put(BS, mapper.toBuildStairDto(buildStair));
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on Build & Stair saving!", ErrorEnum.RUNTIME_ERROR);
        }
        return mapBuildStairDto;
    }
}
