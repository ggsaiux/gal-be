package com.asociatialocatari.gal.cost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/costbs/")
public class CostBuildStairController {

    private static final Logger logger = LoggerFactory.getLogger(CostBuildStairController.class);

    private final CostBuildStairService costBuildStairService;

    private final CostBuildStairRepository costBuildStairRepository;

    public CostBuildStairController(CostBuildStairService costBuildStairService, CostBuildStairRepository costBuildStairRepository) {
        this.costBuildStairService = costBuildStairService;
        this.costBuildStairRepository = costBuildStairRepository;
    }
}
