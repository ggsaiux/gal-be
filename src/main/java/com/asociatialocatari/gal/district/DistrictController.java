package com.asociatialocatari.gal.district;

import com.asociatialocatari.gal.base.ResourceNotFoundException;
import com.asociatialocatari.gal.base.exception.UserException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/districts/")
public class DistrictController {
    private static final Logger logger = LoggerFactory.getLogger(DistrictController.class);

    private final DistrictRepository districtRepository;

    private final String DISTRICTS = "districts";

    private final String DISTRICT = "district";

    public DistrictController(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> getDistricts(){
        try {
            Map<String, List<District>> mapDistrictList = new HashMap<>();
            mapDistrictList.put(DISTRICTS, districtRepository.findAll());
            return new ResponseEntity<>(mapDistrictList, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("city/{cityId}")
    @PreAuthorize("hasAuthorities('ADMINA','ADMINS')")
    public ResponseEntity<?> getDistrictByCityId(@PathVariable long cityId){
        try {
            Map<String, List<District>> mapDistrictList = new HashMap<>();
            mapDistrictList.put(DISTRICTS, (districtRepository.findDistrictsByCity_IdOrderByName(cityId)).get());
            return new ResponseEntity<>(mapDistrictList, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthorities('ADMINA','ADMINS')")
    public ResponseEntity<?> getDistrictById(@PathVariable long id){
        try {
            Map<String, District> mapDistrict = new HashMap<>();
            mapDistrict.put(DISTRICT, districtRepository.findDistrictById(id).get());
            return new ResponseEntity<>(mapDistrict, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> addDistrict(@Valid @RequestBody District district) {
        try {
            return new ResponseEntity<>(districtRepository.save(district), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> saveDistrict(@Valid @RequestBody District district) {
        try {
            return new ResponseEntity<>(districtRepository.save(district), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) throws UserException {
        if(!districtRepository.existsById(id)) {
            throw new ResourceNotFoundException("District not found with id " + id);
        }
        //check if there is a ditrict that depends on the city to be deleted
        return districtRepository.findById(id)
                .map(district -> {
                    districtRepository.delete(district);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("District not found with id " + id));
    }
}
