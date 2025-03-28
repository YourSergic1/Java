package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.datasource.mapper.UserEntityMapper;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.SignUpRequest;
import com.github.YourSergic1.domain.model.User;
import com.github.YourSergic1.domain.model.jwt.JwtAuthentication;
import com.github.YourSergic1.domain.model.jwt.JwtProvider;
import com.github.YourSergic1.domain.model.jwt.JwtRequest;
import com.github.YourSergic1.domain.model.jwt.JwtResponse;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthorizationService {
    final private UserService userService;
    final private JwtProvider jwtProvider;
    final private RepositoryServiceImpl repositoryService;
    private final Map<UUID, String> refreshStorage = new HashMap<>();

    @Autowired
    public AuthorizationService(UserService userService, JwtProvider jwtProvider, RepositoryServiceImpl repositoryService) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.repositoryService = repositoryService;
    }

    public JwtResponse registration(SignUpRequest request) throws AuthException {
        try {
            userService.loadUserByUsername(request.getLogin());
        } catch (UsernameNotFoundException e) {
            userService.registerUser(request);
            User user = UserEntityMapper.EntityToUser(repositoryService.getUser(request.getLogin()).get());

            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);

            refreshStorage.put(user.getId(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        }
        throw new AuthException("Invalid registration. User already exists");
    }

    public JwtResponse authorization(JwtRequest request) throws AuthException {
        try {
            userService.loadUserByUsername(request.getLogin());
        } catch (UsernameNotFoundException e) {
            System.out.println("User doesn't exists.");
            throw new AuthException("User doesn't exists.");
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(request.getPassword(), userDetails.getPassword())) {
            System.out.println("Invalid password.");
            throw new AuthException("Invalid password.");
        }
        User user = UserEntityMapper.EntityToUser(repositoryService.getUser(request.getLogin()).get());
        final String accessToken = jwtProvider.generateAccessToken(user);
        final String refreshToken = jwtProvider.generateRefreshToken(user);

        refreshStorage.put(user.getId(), refreshToken);
        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse updateAccessToken(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getClaims(refreshToken);
            UUID userId = UUID.fromString(claims.get("id").toString());
            final String saveRefreshToken = refreshStorage.get(userId);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                User user = UserEntityMapper.EntityToUser(repositoryService.getUser(userId).get());
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse updateRefreshToken(String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getClaims(refreshToken);
            final UUID userId = (UUID) claims.get("id");
            final String saveRefreshToken = refreshStorage.get(userId);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                User user = UserEntityMapper.EntityToUser(repositoryService.getUser(userId).get());
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(userId, newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT Token");
    }

    public JwtAuthentication getAuthentication() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
