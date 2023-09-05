package com.asociatialocatari.gal.apart;

import com.asociatialocatari.gal.base.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Apartment Controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/aparts/")
public class ApartController {

    private static final Logger logger = LoggerFactory.getLogger(ApartController.class);

    private final ApartService apartService;

    private final ApartRepository apartRepository;

    public ApartController(
            ApartService apartService,
            ApartRepository apartRepository ) {
        this.apartService = apartService;
        this.apartRepository = apartRepository;
    }

    /**
     * List Buildings Stairs by Association
     *
     * @param buildStairId
     * @return
     */
    @GetMapping("bsa/{buildStairId}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getApartsByBuildStair(@PathVariable long buildStairId){
        try {
            return new ResponseEntity<>(apartService.getApartsByBuildStair(buildStairId), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get Apartment by ID
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getApartById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(apartService.getApartById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add Apartment
     *
     * @param apartDto
     * @return
     */
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> addApart(@Valid @RequestBody ApartDto apartDto) {
        try {
            return new ResponseEntity<>(apartService.addApart(apartDto), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update Apartment
     *
     * @param id
     * @param apartDto
     * @return
     */
    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> updateApart(@PathVariable("id") Long id,
                                        @Valid @RequestBody ApartDto apartDto) {
        try {
            apartDto.setId(id);
            return new ResponseEntity<>(apartService.updateApart(apartDto), HttpStatus.OK);
        } catch(Exception e) {
            //todo error handler
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete Apartment
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> deleteApart(@PathVariable Long id) {
        return apartRepository.findById(id)
                .map(apart -> {
                    apartRepository.delete(apart);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Apartment not found with id " + id));
    }
}
