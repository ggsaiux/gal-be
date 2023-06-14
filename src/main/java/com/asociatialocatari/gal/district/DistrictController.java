package com.asociatialocatari.gal.district;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/districts/")
public class DistrictController {
    private static final Logger logger = LoggerFactory.getLogger(DistrictController.class);

    private final DistrictRepository districtRepository;

    private final String DISTRICTS = "districts";

    public DistrictController(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getDistricts(){
        try {
            Map<String, List<District>> mapDistrictList = new HashMap<>();
            mapDistrictList.put(DISTRICTS, districtRepository.findAll());
            return new ResponseEntity<>(mapDistrictList, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
