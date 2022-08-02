package com.technokratos.service.impl;

import com.technokratos.dto.request.*;
import com.technokratos.dto.response.UserResponse;
import com.technokratos.exception.badrequest.BadConfirmationException;
import com.technokratos.exception.badrequest.BadDataException;
import com.technokratos.exception.badrequest.FailureLoginException;
import com.technokratos.exception.badrequest.OccupiedDataException;
import com.technokratos.exception.notfound.AcademyGroupNotFoundException;
import com.technokratos.exception.notfound.UserNotFoundException;
import com.technokratos.model.AcademyGroupEntity;
import com.technokratos.model.UserEntity;
import com.technokratos.repository.AcademyGroupRepository;
import com.technokratos.repository.UserAvatarRepository;
import com.technokratos.repository.UserRepository;
import com.technokratos.service.ConfirmService;
import com.technokratos.service.EmailService;
import com.technokratos.service.FileService;
import com.technokratos.service.UserService;
import com.technokratos.utils.enums.Role;
import com.technokratos.utils.enums.State;
import com.technokratos.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAvatarRepository userAvatarRepository;
    private final AcademyGroupRepository academyGroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final FileService fileService;
    private final ConfirmService confirmService;
    private final EmailService emailService;

    @Override
    public UserResponse getUserById(UUID userId) {
        return userMapper.toUserResponse(
                userRepository.findById(userId)
                        .orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UUID createUser(CreateUserRequest createUserRequest) {
        Optional<UserEntity> user = userRepository.findOneByEmail(createUserRequest.getEmail());
        if (user.isPresent()) {
            throw new OccupiedDataException("Email is occupied: "  + createUserRequest.getEmail());
        }
        UserEntity newUser = userMapper.toUserEntity(createUserRequest);
        newUser.setState(State.NOT_CONFIRMED);
        newUser.setRole(Role.USER);
        UserEntity savedUser = userRepository.save(newUser);
        confirmService.createConfirmCode(savedUser);
        return savedUser.getId();
    }

    @Override
    public UUID confirmUser(UUID confirmCode) {
        UserEntity user = confirmService.getConfirmCode(confirmCode).getUser();
        if (user != null) {
            user.setState(State.CONFIRMED);
            confirmService.updateConfirmCode(user);
            return userRepository.save(user).getId();
        } else {
            throw new BadConfirmationException("Confirmation failure");
        }
    }

    @Override
    public UserResponse createPassword(UUID userId, UserPasswordRequest passwordRequest) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        if (user.getState().name().equals("CONFIRMED")){
            if (passwordRequest.getPassword().equals(passwordRequest.getRepeatPassword())) {
                user.setHashPassword(passwordEncoder.encode(passwordRequest.getPassword()));
                return userMapper.toUserResponse(userRepository.save(user));
            } else {
                throw new BadDataException("Repeat and new password dont match");
            }
        } else {
            throw new BadConfirmationException("Account not confirmed");
        }
    }

    @Override
    public UserResponse login(UserRequest userRequest) {
        return userRepository.findOneByEmail(userRequest.getEmail())
                .filter(user -> passwordEncoder.matches(userRequest.getPassword(), user.getHashPassword()))
                .map(userMapper::toUserResponse)
                .orElseThrow(FailureLoginException::new);
    }

    @Override
    public UserResponse findBySubject(String subject) {
        return userMapper.toUserResponse(userRepository.findOneByEmail(subject)
                .orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserResponse updateUser(UUID userId, UpdateUserRequest userRequest) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        userMapper.toUserEntity(userRequest, user);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void updatePassword(UUID userId, UpdatePasswordRequest passwordRequest) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        if (passwordEncoder.matches(passwordRequest.getOldPassword(), user.getHashPassword())) {
            if (passwordRequest.getNewPassword().equals(passwordRequest.getRepeatPassword())) {
                user.setHashPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
                userRepository.save(user);
            } else {
                throw new BadDataException("Repeat and new passwords dont match");
            }
        } else {
            throw new BadDataException("Old password doesnt match");
        }
    }

    @Override
    public UserResponse updateUserUniversity(UUID userId, UserAcademyGroupRequest userAcademyGroupRequest) {
        log.info("Find user by id '{}' from database", userId);
        UserEntity user = userRepository.findById(userId)
                .map( us -> {
                    log.info("Find academy group by id '{}'", userAcademyGroupRequest.getAcademyGroupId());
                    AcademyGroupEntity academyGroupEntity = academyGroupRepository.findById(userAcademyGroupRequest.getAcademyGroupId())
                                    .orElseThrow(AcademyGroupNotFoundException::new);
                    us.setAcademyGroup(academyGroupEntity);
                    return us;
                })
                .orElseThrow(UserNotFoundException::new);
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
