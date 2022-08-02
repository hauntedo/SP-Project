package com.technokratos.service;

import com.technokratos.dto.request.*;
import com.technokratos.dto.response.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse getUserById(UUID id);

    UUID createUser(CreateUserRequest createUserRequest);

    UUID confirmUser(UUID confirmCode);

    UserResponse createPassword(UUID userId, UserPasswordRequest passwordRequest);

    UserResponse login(UserRequest userRequest);

    UserResponse findBySubject(String subject);


    UserResponse updateUser(UUID userId, UpdateUserRequest userRequest);

    void updatePassword(UUID userId, UpdatePasswordRequest passwordRequest);

    UserResponse updateUserUniversity(UUID userId, UserAcademyGroupRequest userAcademyGroupRequest);
}
