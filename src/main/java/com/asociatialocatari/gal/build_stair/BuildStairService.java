package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.base.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuildStairService {

    private static final Logger logger = LoggerFactory.getLogger(BuildStairService.class);

    private final Utils utils;

    public BuildStairService(Utils utils) {
        this.utils = utils;
    }

    public Map<String, List<BuildStair>>getAllBuildStairByAsso(long id){
        //todo
        return null;
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
