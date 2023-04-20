package com.asociatialocatari.gestiune.asso;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/asso")
public class AssoController {

    private final UserAssoRepository userAssoRepository;
    private final UserAssoService userAssoService;

    public AssoController(UserAssoRepository userAssoRepository, UserAssoService userAssoService) {
        this.userAssoRepository = userAssoRepository;
        this.userAssoService = userAssoService;
    }

    @GetMapping("/{id}")
    public Asso getAsso(Integer id){
        return null;
    }

    @GetMapping("/ua/{lng}")
    @PreAuthorize("hasAuthority('ADMINA')")
    public ResponseEntity<?> getAssoByUser(@PathVariable String lng){
        try{
            return new ResponseEntity<>(userAssoService.getAssoListByUser(lng), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
