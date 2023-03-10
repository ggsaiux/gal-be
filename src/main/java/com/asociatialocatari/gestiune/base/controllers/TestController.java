package com.asociatialocatari.gestiune.base.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/loca")
    @PreAuthorize("hasRole('LOCATAR')")
    public String locaAccess() {
        return "Locatar Board.";
    }

    @GetMapping("/admina")
    @PreAuthorize("hasRole('ADMIN_ASO')")
    public String adminaAccess() {
        return "Association Administrator Board.";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN_SYS')")
    public String adminsAccess() {
        return "System Administrator Board.";
    }
}