package com.github.YourSergic1.domain.model.jwt;

import com.github.YourSergic1.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    private final SecretKey jwtSecret;

    public JwtProvider(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateAccessToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 900000);
        return Jwts.builder()
                .claim("roles", user.getRoles())
                .claim("id", user.getId())
                .setSubject(user.getLogin())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(jwtSecret)
                .compact();
    }

    public String generateRefreshToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 86400000);
        return Jwts.builder()
                .claim("id", user.getId())
                .setSubject(user.getLogin())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(jwtSecret)
                .compact();
    }

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken);
    }

    private boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
