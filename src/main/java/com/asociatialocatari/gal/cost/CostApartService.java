package com.asociatialocatari.gal.cost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CostApartService {

    private final static Logger logger = LoggerFactory.getLogger(CostApartService.class);

    private final CostApartRepository costApartRepository;

    public CostApartService(CostApartRepository costApartRepository) {
        this.costApartRepository = costApartRepository;
    }
}
