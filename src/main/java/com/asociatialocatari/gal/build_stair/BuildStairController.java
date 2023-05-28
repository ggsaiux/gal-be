package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.asso.AssoBuildStairRepository;
import com.asociatialocatari.gal.base.ResourceNotFoundException;
import com.asociatialocatari.gal.base.Utils;
import com.asociatialocatari.gal.base.exception.UserException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/builds/")
public class BuildStairController {
    private static final Logger logger = LoggerFactory.getLogger(BuildStairController.class);

    private final Utils utils;

    private final BuildStairService buildStairService;

    private final BuildStairRepository buildStairRepository;

    private final AssoBuildStairRepository assoBuildStairRepository;

    public BuildStairController(Utils utils, BuildStairService buildStairService, BuildStairRepository buildStairRepository, AssoBuildStairRepository assoBuildStairRepository) {
        this.utils = utils;
        this.buildStairService = buildStairService;
        this.buildStairRepository = buildStairRepository;
        this.assoBuildStairRepository = assoBuildStairRepository;
    }

    @GetMapping("asso/{id}") //district build stair by asso
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getBuildStairs(@PathVariable long assoId){
        try {
            return new ResponseEntity<>(buildStairService.getAllBuildStairByAsso(assoId), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getBuildById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(buildStairService.getBuildStairById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> addBuildStair(@Valid @RequestBody BuildStairDto buildStairDto) {
        try {
            return new ResponseEntity<>(buildStairService.saveBuildStair(buildStairDto), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> saveBuildStair(@PathVariable("id") long id,
                                      @Valid @RequestBody BuildStairDto buildStairDto) {
        try {
            buildStairDto.setId(id);
            return new ResponseEntity<>(buildStairService.saveBuildStair(buildStairDto), HttpStatus.OK);
        } catch(Exception e) {
            //todo error handler
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBuildStair(@PathVariable Long id) throws UserException {
        if(!buildStairRepository.existsById(id)) {
            throw new ResourceNotFoundException("Build - Stair  not found with id " + id);
        }

        return buildStairRepository.findById(id)
                .map(buildStair -> {
                    assoBuildStairRepository.deleteAssoBuildStairByBuildStair(buildStair);
                    buildStairRepository.delete(buildStair);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Asscociation not found with id " + id));
    }

}
