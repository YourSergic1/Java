package com.github.YourSergic1.domain.model.jwt;

import com.github.YourSergic1.domain.model.Role;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class JwtUtil {
    public static JwtAuthentication createJwtAuthentication(Claims claims) {
        final JwtAuthentication jwtAuthentication = new JwtAuthentication();
        List<Role> rolesList = claims.get("roles", List.class);
        Set<Role> roles = new HashSet<>(rolesList);
        jwtAuthentication.setRoles(roles);
        jwtAuthentication.setUuid(claims.get("uuid", UUID.class));
        return jwtAuthentication;
    }
}