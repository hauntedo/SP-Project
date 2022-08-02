package com.technokratos.provider;

import com.technokratos.dto.response.UserResponse;
import com.technokratos.model.RefreshTokenEntity;

import java.util.UUID;

public interface JwtRefreshTokenProvider {

    UUID generateRefreshToken(UserResponse userResponse);

    RefreshTokenEntity verifyRefreshTokenExpiration(UUID refreshToken);
}
