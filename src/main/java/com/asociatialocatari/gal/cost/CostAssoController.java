package com.asociatialocatari.gal.cost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/costas/")
public class CostAssoController {

    public static final Logger logger = LoggerFactory.getLogger(CostAssoController.class);

    public final CostAssoService costAssoService;

    public final CostAssoRepository costAssoRepository;


    public CostAssoController(CostAssoService costAssoService, CostAssoRepository costAssoRepository) {
        this.costAssoService = costAssoService;
        this.costAssoRepository = costAssoRepository;
    }
}
