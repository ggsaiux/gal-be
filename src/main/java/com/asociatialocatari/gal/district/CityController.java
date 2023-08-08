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

@RestController
@RequestMapping("/api/cities/")
public class CityController {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    private final CityRepository cityRepository;

    private final String CITIES = "cities";

    private final String CITY = "city";

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getCities(){
        try {
            Map<String, List<City>> mapCityList = new HashMap<>();
            mapCityList.put(CITIES, cityRepository.findAll());
            return new ResponseEntity<>(mapCityList, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getCityById(@PathVariable long id){
        try {
            Map<String, City> mapCity = new HashMap<>();
            mapCity.put(CITY, cityRepository.findCityById(id).get());
            return new ResponseEntity<>(mapCity, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> addCity(@Valid @RequestBody City city) {
        try {
            //todo map maybe
            return new ResponseEntity<>(cityRepository.save(city), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> saveCity(@Valid @RequestBody City city) {
        try {
            //todo map maybe
            return new ResponseEntity<>(cityRepository.save(city), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) throws UserException {
        if(!cityRepository.existsById(id)) {
            throw new ResourceNotFoundException("City not found with id " + id);
        }
        //check if there is a ditrict that depends on the city to be deleted
        return cityRepository.findById(id)
                .map(city -> {
                    cityRepository.delete(city);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("City not found with id " + id));
    }
}
