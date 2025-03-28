package com.github.YourSergic1.web.controller.jwt;

import com.github.YourSergic1.domain.model.jwt.JwtResponse;
import com.github.YourSergic1.domain.model.jwt.RefreshJwtRequest;
import com.github.YourSergic1.domain.service.AuthorizationService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestController {
    @Autowired
    private final AuthorizationService authorizationService;

    @PostMapping("/token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authorizationService.updateAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> postMethodName(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authorizationService.updateRefreshToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
