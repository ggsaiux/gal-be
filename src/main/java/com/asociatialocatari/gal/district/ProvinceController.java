package com.asociatialocatari.gal.district;

import com.asociatialocatari.gal.base.ResourceNotFoundException;
import com.asociatialocatari.gal.base.exception.UserException;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/provinces/")
public class ProvinceController {
    private static final Logger logger = LoggerFactory.getLogger(ProvinceController.class);

    private final ProvinceMapper mapper = Mappers.getMapper(ProvinceMapper.class);

    private final ProvinceRepository provinceRepository;

    private final String PROVINCES = "provinces";

    private final String PROVINCE = "province";

    public ProvinceController(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    /**
     * for AssoForm AssoOutDto list
     * @return
     */
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMINS','ADMINA')")
    public ResponseEntity<?> getProvinces(){
        try {
            Map<String, List<ProvinceDto>> mapProvinceList = new HashMap<>();
/*
            List<Province> provinceList = provinceRepository.findAll();
            List<ProvinceDto> provinceDtoList = new ArrayList<>();
            for(Province province : provinceList){
                provinceDtoList.add(mapper.toProvinceDto(province));
            }
            mapProvinceList.put(PROVINCES, provinceDtoList);
*/
            mapProvinceList.put(PROVINCES,
                    (provinceRepository.findAll())
                    .stream()
                    .sorted(Comparator.comparing(Province::getName))
                    .map(mapper::toProvinceDto)
                    .collect(Collectors.toCollection(ArrayList::new)));
            return new ResponseEntity<>(mapProvinceList, HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * todo get all provinces to manage them ADMINS
     * @param
     * @return
     */

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("id")
    @PreAuthorize("hasAuthorities('ADMINA','ADMINS')")
    public ResponseEntity<?> getProvinceById(@PathVariable long id){
        try {
            Map<String, Province> mapProvince = new HashMap<>();
            mapProvince.put(PROVINCE, provinceRepository.findProvinceById(id).get());
            return new ResponseEntity<>(mapProvince, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> addProvince(@Valid @RequestBody Province province) {
        try {
            return new ResponseEntity<>(provinceRepository.save(province), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> saveProvince(@Valid @RequestBody Province province) {
        try {
            return new ResponseEntity<>(provinceRepository.save(province), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseEntity<?> deleteProvince(@PathVariable Long id) throws UserException {
        if(!provinceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Province not found with id " + id);
        }
        //check if there is a city that depends on the province to be deleted
        return provinceRepository.findById(id)
                .map(province -> {
                    provinceRepository.delete(province);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("City not found with id " + id));
    }
}
