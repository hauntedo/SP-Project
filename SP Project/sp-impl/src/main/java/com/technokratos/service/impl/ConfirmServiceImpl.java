package com.technokratos.service.impl;

import com.technokratos.exception.notfound.ConfirmCodeNotFoundException;
import com.technokratos.model.ConfirmCodeEntity;
import com.technokratos.model.UserEntity;
import com.technokratos.repository.ConfirmCodeRepository;
import com.technokratos.service.ConfirmService;
import com.technokratos.service.EmailService;
import com.technokratos.utils.enums.ConfirmCodeState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.technokratos.utils.enums.ConfirmCodeState.EXPIRED;
import static com.technokratos.utils.enums.ConfirmCodeState.NOT_EXPIRED;

@Service
@RequiredArgsConstructor
public class ConfirmServiceImpl implements ConfirmService {

    private final ConfirmCodeRepository confirmCodeRepository;
    private final EmailService emailService;

    @Override
    public void createConfirmCode(UserEntity user) {
        ConfirmCodeEntity confirmCode = ConfirmCodeEntity.builder()
                .user(user)
                .confirmCode(UUID.randomUUID())
                .confirmCodeState(ConfirmCodeState.NOT_EXPIRED)
                .build();
        Map<String, String> emailData = new HashMap<>();
        emailData.put("user_email", user.getEmail());
        emailData.put("user_firstname", user.getFirstName());
        emailData.put("confirm_code", confirmCode.getConfirmCode().toString());
        emailService.send(emailData, "Confirm account");
        confirmCodeRepository.save(confirmCode);
    }

    @Override
    public void updateConfirmCode(UserEntity user) {
        Optional<ConfirmCodeEntity> confirmCode = confirmCodeRepository.findOneByUserId(user.getId());
        if (confirmCode.isPresent()) {
            if (confirmCode.get().getConfirmCodeState() == EXPIRED) {
                UUID newConfirmCode = UUID.randomUUID();
                confirmCode.get().setConfirmCode(newConfirmCode);
                confirmCode.get().setConfirmCodeState(NOT_EXPIRED);
            } else {
                confirmCode.get().setConfirmCodeState(EXPIRED);
            }
            confirmCodeRepository.save(confirmCode.get());
        } else {
            throw new ConfirmCodeNotFoundException();
        }
    }

    @Override
    public ConfirmCodeEntity getConfirmCode(UUID confirmCode) {
        return confirmCodeRepository.findOneByConfirmCode(confirmCode)
                .orElseThrow(ConfirmCodeNotFoundException::new);
    }
}
