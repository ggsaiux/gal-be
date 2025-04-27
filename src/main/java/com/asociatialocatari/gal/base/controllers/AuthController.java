package com.asociatialocatari.gal.base.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.asociatialocatari.gal.base.models.*;
import com.asociatialocatari.gal.base.payload.request.LoginRequest;
import com.asociatialocatari.gal.base.payload.request.SignupRequest;
import com.asociatialocatari.gal.base.payload.response.JwtResponse;
import com.asociatialocatari.gal.base.payload.response.MessageResponse;
import com.asociatialocatari.gal.base.repositories.*;
import com.asociatialocatari.gal.base.security.jwt.JwtUtils;
import com.asociatialocatari.gal.base.security.services.UserDetailsImpl;
import com.asociatialocatari.gal.base.security.services.UserDetailsServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String DEFAULT_ROLE = "USER";
    private static final String DEFAULT_LNG = "ro";
    private static final String DEFAULT_STT = "active";
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    final AuthenticationManager authenticationManager;

    final UserRepository userRepository;

    final RoleRepository roleRepository;

    final PasswordEncoder encoder;

    final JwtUtils jwtUtils;

    final LngRepository lngRepository;

    final SttRepository sttRepository;

    final UserRoleRepository userRoleRepository;

    final UserDetailsServiceImpl userDetailsService;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder encoder,
            JwtUtils jwtUtils,
            LngRepository lngRepository,
            SttRepository sttRepository,
            UserRoleRepository userRoleRepository,
            UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.lngRepository = lngRepository;
        this.sttRepository = sttRepository;
        this.userRoleRepository = userRoleRepository;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        //UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(loginRequest.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        //Set<String> strRoles = signUpRequest.getRole();
        //Set<Role> roles = new HashSet<>();

        try {
/*
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {

                        case "user":
                            Role userRole = roleRepository.findByName(ERole.USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);

                            break;

                        case "admina":
                            Role adminaRole = roleRepository.findByName(ERole.ADMIN_ASSO)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminaRole);

                            break;

                        case "admins":
                            Role adminsRole = roleRepository.findByName(ERole.ADMIN_SYS)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminsRole);

                            break;
                        case "loca":
                            Role modRole = roleRepository.findByName(ERole.LOCATAR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);

                            break;
                        default:
                            Role defaultRole = roleRepository.findByName(ERole.USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(defaultRole);
                    }
                });
            }
            user.setRoles(roles);
 */

            Role defaultRole = roleRepository.findByName(DEFAULT_ROLE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            Lng defaultLng = lngRepository.findByAbbrv(DEFAULT_LNG)
                    .orElseThrow(() -> new RuntimeException("Error: Language is not found."));
            Stt defaultStt = sttRepository.findByName(DEFAULT_STT)
                    .orElseThrow(() -> new RuntimeException("Error: State is not found."));
            //user.setRole(defaultRole);
            user.setLng(defaultLng);
            user.setStt(defaultStt);

            User userUp = userRepository.saveAndFlush(user);
            UserRole userRole = new UserRole();
            userRole.setUser(userUp);
            userRole.setRole(defaultRole);
            userRole.setStt(defaultStt);
            userRoleRepository.saveAndFlush(userRole);
        } catch(Exception e){
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
