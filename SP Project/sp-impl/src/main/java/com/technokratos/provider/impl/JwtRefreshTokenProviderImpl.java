package com.technokratos.provider.impl;

import com.technokratos.dto.response.UserResponse;
import com.technokratos.exception.unauthorized.RefreshTokenNotFoundException;
import com.technokratos.exception.notfound.UserNotFoundException;
import com.technokratos.exception.unauthorized.TokenRefreshException;
import com.technokratos.model.RefreshTokenEntity;
import com.technokratos.model.UserEntity;
import com.technokratos.provider.JwtRefreshTokenProvider;
import com.technokratos.repository.UserRefreshTokenRepository;
import com.technokratos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtRefreshTokenProviderImpl implements JwtRefreshTokenProvider {

    @Value("${jwt.expiration.refresh.mills}")
    private Long expirationRefreshInMills;

    private final UserRefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Override
    public UUID generateRefreshToken(UserResponse userResponse) {
        UserEntity user = userRepository.findById(userResponse.getId())
                .orElseThrow(UserNotFoundException::new);
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .account(user)
                .expiryDate(Instant.now().plusMillis(expirationRefreshInMills))
                .build();
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getId();
    }

    @Override
    public RefreshTokenEntity verifyRefreshTokenExpiration(UUID refreshTokenId) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findById(refreshTokenId)
                .orElseThrow(RefreshTokenNotFoundException::new);

        refreshTokenRepository.delete(refreshToken);

        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            throw new TokenRefreshException(String.valueOf(refreshToken.getId()), "Refresh token expired.");
        }

        return refreshTokenRepository.save(
                RefreshTokenEntity.builder()
                        .expiryDate(Instant.now().plusMillis(expirationRefreshInMills))
                        .account(refreshToken.getAccount())
                        .build());
    }
}
