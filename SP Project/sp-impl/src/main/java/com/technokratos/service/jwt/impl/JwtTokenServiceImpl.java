package com.technokratos.service.jwt.impl;

import com.technokratos.dto.TokenPairRequest;
import com.technokratos.dto.TokenPairResponse;
import com.technokratos.dto.response.UserResponse;
import com.technokratos.model.RefreshTokenEntity;
import com.technokratos.provider.JwtAccessTokenProvider;
import com.technokratos.provider.JwtRefreshTokenProvider;
import com.technokratos.service.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

import static com.technokratos.consts.ApiConst.BEARER;
import static com.technokratos.consts.ApiConst.ROLE;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final JwtRefreshTokenProvider jwtRefreshTokenProvider;

    @Override
    public UserResponse getUserByToken(String token) {
        return jwtAccessTokenProvider.getUserByToken(getValidToken(token));
    }

    @Override
    public TokenPairResponse generateTokenCouple(UserResponse accountResponse) {
        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                accountResponse.getEmail(),
                Collections.singletonMap(ROLE, accountResponse.getRole())
        );
        UUID refreshToken = jwtRefreshTokenProvider.generateRefreshToken(accountResponse);
        return TokenPairResponse.builder()
                .accessToken(BEARER.concat(accessToken))
                .refreshToken(refreshToken)
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }

    @Override
    public TokenPairResponse refreshAccessToken(TokenPairRequest tokenPairRequest) {
        String role = jwtAccessTokenProvider.getRoleFromAccessToken(getValidToken(tokenPairRequest.getAccessToken()));

        RefreshTokenEntity verifiedRefreshToken = jwtRefreshTokenProvider.
                verifyRefreshTokenExpiration(UUID.fromString(tokenPairRequest.getRefreshToken()));

        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                jwtAccessTokenProvider.getSubjectFromAccessToken(getValidToken(tokenPairRequest.getAccessToken())),
                Collections.singletonMap(ROLE, role));

        return TokenPairResponse.builder()
                .refreshToken(verifiedRefreshToken.getId())
                .accessToken(BEARER.concat(accessToken))
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }

    @Override
    public String getValidToken(String token) {
        if (token.startsWith("Bearer ")) {
            return StringUtils.removeStart(token, "Bearer".trim());
        } else {
            return token;
        }
    }
}
