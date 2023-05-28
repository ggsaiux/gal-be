package com.asociatialocatari.gal.asso;

import com.asociatialocatari.gal.base.Utils;
import com.asociatialocatari.gal.base.exception.UserException;
import com.asociatialocatari.gal.base.models.User;
import com.asociatialocatari.gal.base.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/assos/")
public class AssoController {

    private final UserAssoRepository userAssoRepository;

    private final UserAssoServiceImpl userAssoServiceImpl;

    private final AssoService assoService;
    private final AssoRepository assoRepository;
    private final Utils utils;

    public AssoController(UserAssoRepository userAssoRepository, UserAssoServiceImpl userAssoServiceImpl, AssoService assoService, AssoRepository assoRepository, Utils utils) {
        this.userAssoRepository = userAssoRepository;
        this.userAssoServiceImpl = userAssoServiceImpl;
        this.assoService = assoService;
        this.assoRepository = assoRepository;
        this.utils = utils;
    }

    @GetMapping("ua") //user's assos
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getAssoByUser(){
        try {
            return new ResponseEntity<>(userAssoServiceImpl.getAssoListByUser(), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getAssoById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(assoService.getAssoById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> addAsso(@Valid @RequestBody AssoDto assoDto) {
        try {
            return new ResponseEntity<>(assoService.saveAsso(assoDto), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> saveAsso(@PathVariable("id") long id,
                                      @Valid @RequestBody AssoDto assoDto) {
        try {
            assoDto.setId(id);
            return new ResponseEntity<>(assoService.saveAsso(assoDto), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsso(@PathVariable Long id) throws UserException {
        if(!assoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Association not found with id " + id);
        }
        User user = utils.getUserFromContext();
        return assoRepository.findById(id)
                .map(asso -> {
                    userAssoRepository.deleteUserAssoByUserAndAsso(user, asso);
                    assoRepository.delete(asso);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Asscociation not found with id " + id));
    }
}
