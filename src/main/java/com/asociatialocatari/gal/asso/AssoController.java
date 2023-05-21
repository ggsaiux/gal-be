package com.asociatialocatari.gal.asso;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/assos/")
public class AssoController {

    private final UserAssoRepository userAssoRepository;

    private final UserAssoServiceImpl userAssoServiceImpl;

    private final AssoServiceImpl assoServiceImpl;

    public AssoController(UserAssoRepository userAssoRepository, UserAssoServiceImpl userAssoServiceImpl, AssoServiceImpl assoServiceImpl) {
        this.userAssoRepository = userAssoRepository;
        this.userAssoServiceImpl = userAssoServiceImpl;
        this.assoServiceImpl = assoServiceImpl;
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
            return new ResponseEntity<>(assoServiceImpl.getAssoById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> addAsso(@Valid @RequestBody AssoDto assoDto) {
        try {
            return new ResponseEntity<>(assoServiceImpl.saveAsso(assoDto), HttpStatus.OK);
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
            return new ResponseEntity<>(assoServiceImpl.saveAsso(assoDto), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
