package com.asociatialocatari.gestiune.base;

import com.asociatialocatari.gestiune.base.exception.UserException;
import com.asociatialocatari.gestiune.base.models.User;
import com.asociatialocatari.gestiune.base.repositories.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Utils {

    private static final Log log = LogFactory.getLog(Utils.class);

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    private final UserRepository userRepository;

    public Utils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
/*
    public String getClientIpAddress() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
        return ip;
    }
*/
    public User getUserFromContext() throws UserException {

        Optional<User> userOpt = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            //String currentUserName = authentication.getName();
            userOpt = userRepository.findByUsername(authentication.getName());
        }
        if (userOpt != null)
            return userOpt.get();
        else {
            log.error("ERROR: Utente non trovato!");
            throw new UserException("Utente non trovato!");
        }
    }

    public Set<String> getAuthoritiesFromContext() throws UserException {

        Set<String> roles = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            roles = authentication.getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toSet());
        }
        if (roles != null && !roles.isEmpty())
            return roles;
        else {
            log.error("ERROR: Utente senza ruoli!");
            throw new UserException("ERROR: Utente senza ruoli!");
        }
    }
}