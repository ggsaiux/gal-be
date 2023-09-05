package com.asociatialocatari.gal.cost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/costap/")
public class CostApartController {
    private static final Logger logger = LoggerFactory.getLogger(CostApartController.class);

    private final CostApartService costApartService;

    private final CostApartRepository costApartRepository;

    public CostApartController(CostApartService costApartService, CostApartRepository costApartRepository) {
        this.costApartService = costApartService;
        this.costApartRepository = costApartRepository;
    }
}
