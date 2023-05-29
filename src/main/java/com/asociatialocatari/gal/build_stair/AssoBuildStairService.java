package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.asso.Asso;
import com.asociatialocatari.gal.asso.AssoRepository;
import com.asociatialocatari.gal.base.exception.GalException;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.asociatialocatari.gal.base.exception.ErrorEnum.RUNTIME_ERROR;

@Service
public class AssoBuildStairService {

    private static final Logger logger = LoggerFactory.getLogger(AssoBuildStairService.class);

    private final AssoRepository assoRepository;

    private final AssoBuildStairRepository assoBuildStairRepository;

    private final BuildStairMapper mapper = Mappers.getMapper(BuildStairMapper.class);

    //BSS = Builds Stairs
    private final String BSS = "buildsStairs";

    public AssoBuildStairService(AssoRepository assoRepository, AssoBuildStairRepository assoBuildStairRepository) {
        this.assoRepository = assoRepository;
        this.assoBuildStairRepository = assoBuildStairRepository;
    }

    public Map<String, List<BuildStairDto>> getBuildsStairsByAsso(long id) throws GalException {
        //todo
        Optional<Asso> assoOpt = assoRepository.findById(id);

        if(!assoOpt.isPresent())
            throw new GalException("Error on builds stairs listing!", RUNTIME_ERROR);

        Map<String,List<BuildStairDto>> mapBuildStairDtoList = new HashMap<>();
        List<BuildStairDto> buildStairDtoList = new ArrayList<>();
        try {
            List<AssoBuildStair> assoBuildStairList = assoBuildStairRepository.findAssoBuildStairByAsso(assoOpt.get());
            for (AssoBuildStair assoBuildStair : assoBuildStairList) {
                buildStairDtoList.add(mapper.toBuildStairDto(assoBuildStair.getBuildStair()));
            }
            mapBuildStairDtoList.put(BSS, buildStairDtoList);
        } catch(Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
            throw new GalException("Error on builds stairs listing!", RUNTIME_ERROR);
        }
        return mapBuildStairDtoList;
    }
}
