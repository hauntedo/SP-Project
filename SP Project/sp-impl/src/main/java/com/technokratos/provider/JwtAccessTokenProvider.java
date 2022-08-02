package com.technokratos.provider;

import com.technokratos.dto.response.UserResponse;
import com.technokratos.exception.unauthorized.AuthenticationHeaderException;

import java.util.Date;
import java.util.Map;

public interface JwtAccessTokenProvider {

    String generateAccessToken(String subject, Map<String, Object> data);

    boolean validateAccessToken(String accessToken) throws AuthenticationHeaderException;

    UserResponse getUserByToken(String token) throws AuthenticationHeaderException;

    String getRoleFromAccessToken(String accessToken);

    Date getExpirationDateFromAccessToken(String accessToken);

    String getSubjectFromAccessToken(String accessToken);

}
