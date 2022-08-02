package com.technokratos.service.impl;

import com.technokratos.dto.request.UpdateCvRequest;
import com.technokratos.dto.response.CvResponse;
import com.technokratos.exception.badrequest.BadFileException;
import com.technokratos.exception.notfound.UserNotFoundException;
import com.technokratos.model.CvEntity;
import com.technokratos.model.FileEntity;
import com.technokratos.model.UserEntity;
import com.technokratos.repository.CvRepository;
import com.technokratos.repository.UserRepository;
import com.technokratos.service.CvService;
import com.technokratos.service.FileService;
import com.technokratos.service.SkillService;
import com.technokratos.utils.enums.FileType;
import com.technokratos.utils.mapper.CvMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class CvServiceImpl implements CvService {

    private final UserRepository userRepository;
    private final FileService fileService;
    private final CvRepository cvRepository;
    private final SkillService skillService;

    private final CvMapper cvMapper;

    public CvServiceImpl(UserRepository userRepository, FileService fileService,
                         CvRepository cvRepository, SkillService skillService,
                         @Qualifier("customCvMapper") CvMapper cvMapper) {
        this.userRepository = userRepository;
        this.fileService = fileService;
        this.cvRepository = cvRepository;
        this.skillService = skillService;
        this.cvMapper = cvMapper;
    }

    @Override
    public CvResponse updateCvInfo(UUID userId, UpdateCvRequest request) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        CvEntity cv = cvRepository.findByUserId(userId)
                .map(cv1 -> {
                    cvMapper.toCvEntity(request, cv1);
                    return cv1;
                })
                .map(cv2 -> {
                    cv2.setSkills(skillService.getSkillsBySkillIdSet(request.getSkills()));
                    return cv2;
                })
                .orElse(
                        cvMapper.toCvEntity(request)
                );
        if (Objects.isNull(cv.getUser())) {
            cv.setUser(user);
        }
        return cvMapper.toCvResponse(cvRepository.save(cv));
    }

    private FileEntity getFileFromRequest(UUID cvFileId) {
        FileEntity file = fileService.getFile(cvFileId);
        if (file.getFileType() != FileType.CV_FILE) {
            throw new BadFileException("Incorrect file type");
        }
        return file;
    }

}
