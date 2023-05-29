package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.asso.Asso;
import com.asociatialocatari.gal.asso.AssoRepository;
import com.asociatialocatari.gal.base.Utils;
import com.asociatialocatari.gal.base.exception.GalException;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.asociatialocatari.gal.base.exception.ErrorEnum.RUNTIME_ERROR;

@Service
public class BuildStairService {

    private static final Logger logger = LoggerFactory.getLogger(BuildStairService.class);

    private final BuildStairMapper mapper = Mappers.getMapper(BuildStairMapper.class);

    //BSS = Builds Stairs
    private final String BSS = "buildsStairs";

    public BuildStairService() {


    }

    public Map<String, BuildStair> getBuildStairById(long id){
        //todo
        return null;
    }

    public Map<String, BuildStair> saveBuildStair(BuildStairDto buildStairDto){
        //todo
        return null;
    }

}
