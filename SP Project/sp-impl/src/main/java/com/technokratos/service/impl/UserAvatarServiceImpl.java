package com.technokratos.service.impl;

import com.technokratos.exception.badrequest.BadFileException;
import com.technokratos.exception.notfound.UserAvatarNotFoundException;
import com.technokratos.exception.notfound.UserNotFoundException;
import com.technokratos.model.FileEntity;
import com.technokratos.model.UserAvatarEntity;
import com.technokratos.model.UserEntity;
import com.technokratos.repository.UserAvatarRepository;
import com.technokratos.repository.UserRepository;
import com.technokratos.service.FileService;
import com.technokratos.service.UserAvatarService;
import com.technokratos.utils.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAvatarServiceImpl implements UserAvatarService {

    private final FileService fileService;
    private final UserRepository userRepository;

    private final UserAvatarRepository userAvatarRepository;

    @Override
    public UUID getPhoto(UUID userId) {
        UserAvatarEntity userAvatar = userAvatarRepository.findByUserId(userId)
                .orElseThrow(UserAvatarNotFoundException::new);
        return userAvatar.getUserAvatar().getId();
    }

    @Override
    public UUID saveUserPhoto(UUID userId, UUID fileId) {
        FileEntity file = fileService.getFile(fileId);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        if (file.getFileType() != FileType.USER_PHOTO) {
            throw new BadFileException("Incorrect file type");
        }
        UserAvatarEntity userAvatar = userAvatarRepository.findByUserId(userId)
                .map(userAvatarEntity -> {
                    userAvatarEntity.setUserAvatar(file);
                    return userAvatarEntity;
                })
                .orElse(
                        UserAvatarEntity.builder()
                                .userAvatar(file)
                                .user(user)
                                .build());
        return userAvatarRepository.save(userAvatar).getId();
    }
}
