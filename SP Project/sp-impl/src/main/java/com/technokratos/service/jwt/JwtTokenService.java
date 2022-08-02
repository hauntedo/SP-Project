package com.technokratos.service.jwt;

import com.technokratos.dto.TokenPairRequest;
import com.technokratos.dto.TokenPairResponse;
import com.technokratos.dto.response.UserResponse;

public interface JwtTokenService {
    UserResponse getUserByToken(String token);

    TokenPairResponse generateTokenCouple(UserResponse accountResponse);

    TokenPairResponse refreshAccessToken(TokenPairRequest tokenPairRequest);

    String getValidToken(String token);
}
