package com.technokratos.controllers;

import com.technokratos.api.JwtTokenApi;
import com.technokratos.dto.TokenPairRequest;
import com.technokratos.dto.TokenPairResponse;
import com.technokratos.dto.response.UserResponse;
import com.technokratos.service.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JwtTokenController implements JwtTokenApi {

    private final JwtTokenService jwtTokenService;

    @Override
    public ResponseEntity<UserResponse> userInfoByToken(String token){
        return ResponseEntity.ok(jwtTokenService.getUserByToken(token));
    }

    @Override
    public ResponseEntity<TokenPairResponse> updateTokens(TokenPairRequest tokenPairRequest) {
        return ResponseEntity.ok(jwtTokenService.refreshAccessToken(tokenPairRequest));
    }
}