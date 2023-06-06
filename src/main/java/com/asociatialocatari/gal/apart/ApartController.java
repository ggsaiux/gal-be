package com.asociatialocatari.gal.apart;

import com.asociatialocatari.gal.base.ResourceNotFoundException;
import com.asociatialocatari.gal.base.exception.UserException;
import com.asociatialocatari.gal.build_stair.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

public class ApartController {

    private static final Logger logger = LoggerFactory.getLogger(ApartController.class);

    private final BuildStairApartService buildStairApartService;

    private final ApartService apartService;

    private final ApartRepository apartRepository;

    private final BuildStairApartRepository buildStairApartRepository;

    public ApartController(
            BuildStairApartService buildStairApartService,
            ApartService apartService,
            ApartRepository apartRepository,
            BuildStairApartRepository buildStairApartRepository) {
        this.buildStairApartService = buildStairApartService;
        this.apartService = apartService;
        this.apartRepository = apartRepository;
        this.buildStairApartRepository = buildStairApartRepository;
    }

    /**
     * get builds stairs by asso
     * @param buildStairId
     * @return
     */
    @GetMapping("bsa/{buildStairId}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getApartsByBuildStair(@PathVariable long buildStairId){
        try {
            return new ResponseEntity<>(buildStairApartService.getApartsByBuildStair(buildStairId), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getApartyId(@PathVariable long id) {
        try {
            return new ResponseEntity<>(apartService.getApartById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id  - buildStairId
     * @param apartDto
     * @return
     */
    @PostMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> addApart(@PathVariable("id") long id,
                                      @Valid @RequestBody ApartDto apartDto) {
        try {
            return new ResponseEntity<>(apartService.saveApart(apartDto, id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id - buildStairId
     * @param apartDto
     * @return
     */
    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> saveApart(@PathVariable("id") long id,
                                            @Valid @RequestBody ApartDto apartDto) {
        try {
            apartDto.setId(id);
            return new ResponseEntity<>(apartService.saveApart(apartDto, id), HttpStatus.OK);
        } catch(Exception e) {
            //todo error handler
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> deleteApart(@PathVariable Long id) {
        if(!apartRepository.existsById(id)) {
            throw new ResourceNotFoundException("Build - Stair  not found with id " + id);
        }

        return apartRepository.findById(id)
                .map(apart -> {
                    buildStairApartRepository.deleteBuildStairApartByApart(apart);
                    apartRepository.delete(apart);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Asscociation not found with id " + id));
    }

}
