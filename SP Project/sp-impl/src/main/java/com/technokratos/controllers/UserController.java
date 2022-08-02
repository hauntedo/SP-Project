package com.technokratos.controllers;

import com.technokratos.api.UserApi;
import com.technokratos.dto.TokenPairResponse;
import com.technokratos.dto.request.*;
import com.technokratos.dto.response.*;
import com.technokratos.model.FileEntity;
import com.technokratos.service.*;
import com.technokratos.service.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService userService;
    private final FileService fileService;
    private final UserAvatarService userAvatarService;
    private final JwtTokenService jwtTokenService;

    private final CvService cvService;
    private final CourseService courseService;

    @Override
    public ResponseEntity<UserResponse> getUserById(UUID userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Override
    public ResponseEntity<GridFsResource> getPhotoByUserId(UUID userId) {
        FileEntity file = fileService.getFile(userAvatarService.getPhoto(userId));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "filename=\"" + file.getOriginalFileName() + "\"")
                .body(fileService.getFileFromStorage(file.getObjectId()));
    }

    @Override
    public ResponseEntity<UUID> registration(CreateUserRequest createUserRequest) {
        return ResponseEntity.status(201)
                .body(userService.createUser(createUserRequest));
    }

    @Override
    public ResponseEntity<UUID> confirm(UUID confirmCode) {
        return ResponseEntity.ok(userService.confirmUser(confirmCode));
    }

    @Override
    public ResponseEntity<TokenPairResponse> createPassword(UUID userId, UserPasswordRequest passwordRequest) {
        return ResponseEntity.ok(
                jwtTokenService.generateTokenCouple(
                userService.createPassword(userId, passwordRequest)));
    }

    @Override
    public ResponseEntity<TokenPairResponse> login(UserRequest userRequest) {
        return ResponseEntity.ok(jwtTokenService.generateTokenCouple(userService.login(userRequest)));
    }

    @Override
    public ResponseEntity<UUID> saveUserPhoto(UUID userId, UUID fileID) {
        return ResponseEntity
                .status(201)
                .body(userAvatarService.saveUserPhoto(userId, fileID));
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UUID userId, UpdateUserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }

    @Override
    public ResponseEntity<SuccessResponse> updateUserPassword(UUID userId, UpdatePasswordRequest passwordRequest) {
        userService.updatePassword(userId, passwordRequest);
        return ResponseEntity.status(201)
                .body(
                        SuccessResponse.builder()
                                .message("Password updated successfully")
                                .time(Instant.now())
                                .build()
                );
    }

    @Override
    public ResponseEntity<UUID> updateUserPhoto(UUID userId, UUID fileId) {
        return ResponseEntity.ok(userAvatarService.saveUserPhoto(userId, fileId));
    }

    @Override
    public ResponseEntity<CvResponse> updateUserCv(UUID userId, UpdateCvRequest request) {
        return ResponseEntity.ok(cvService.updateCvInfo(userId, request));
    }

    @Override
    public ResponseEntity<PageResponse<CourseResponse>> getUserCourses(UUID userId, int page, int size) {
        return ResponseEntity.ok(courseService.getUserCourses(userId, page, size));
    }

    @Override
    public ResponseEntity<UserResponse> updateUserUniversity(UUID userId, UserAcademyGroupRequest userAcademyGroupRequest) {
        userService.updateUserUniversity(userId, userAcademyGroupRequest);
        return ResponseEntity.ok(userService.updateUserUniversity(userId, userAcademyGroupRequest));
    }
}
