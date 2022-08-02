package com.technokratos.service;

import com.technokratos.model.ConfirmCodeEntity;
import com.technokratos.model.UserEntity;

import java.util.UUID;

public interface ConfirmService {

    void createConfirmCode(UserEntity user);
    void updateConfirmCode(UserEntity user);

    ConfirmCodeEntity getConfirmCode(UUID confirmCode);

}
