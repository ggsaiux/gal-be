package com.asociatialocatari.gal.build_stair;

import com.asociatialocatari.gal.base.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bss/")
public class BuildStairController {
    private static final Logger logger = LoggerFactory.getLogger(BuildStairController.class);

    private final BuildStairService buildStairService;

    private final BuildStairRepository buildStairRepository;

    public BuildStairController(BuildStairService buildStairService,
                                BuildStairRepository buildStairRepository) {
        this.buildStairService = buildStairService;
        this.buildStairRepository = buildStairRepository;
    }

    /**
     * List Buildings Satirs by Association
     *
     * @param assoId
     * @return
     */
    @GetMapping("abs/{assoId}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getBuildsStairsByAsso(@PathVariable Long assoId){
        try {
            return new ResponseEntity<>(buildStairService.getBuildsStairsByAsso(assoId), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get Building Stair by ID
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getBuildStairById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(buildStairService.getBuildStairById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add Building Stair
     *
     * @param buildStairDto
     * @return
     */
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> addBuildStair(@Valid @RequestBody BuildStairDto buildStairDto) {
        try {
            return new ResponseEntity<>(buildStairService.addBuildStair(buildStairDto), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update Building Stair
     *
     * @param id
     * @param buildStairDto
     * @return
     */
    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> updateBuildStair(@PathVariable("id") Long id,
                                      @Valid @RequestBody BuildStairDto buildStairDto) {
        try {
            buildStairDto.setId(id);
            return new ResponseEntity<>(buildStairService.updateBuildStair(buildStairDto), HttpStatus.OK);
        } catch(Exception e) {
            //todo error handler
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete Building Stair
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> deleteBuildStair(@PathVariable Long id) {
        return buildStairRepository.findById(id)
                .map(buildStair -> {
                    buildStairRepository.delete(buildStair);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Building Stair not found with id " + id));
    }
}
