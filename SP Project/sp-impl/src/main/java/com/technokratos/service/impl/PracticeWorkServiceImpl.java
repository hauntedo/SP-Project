package com.technokratos.service.impl;

import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.PracticeWorkResponse;
import com.technokratos.exception.notfound.CourseNotFoundException;
import com.technokratos.exception.notfound.PracticeWorkNotFoundException;
import com.technokratos.model.PracticeWorkEntity;
import com.technokratos.repository.CourseRepository;
import com.technokratos.repository.PracticeWorkRepository;
import com.technokratos.service.PracticeWorkService;
import com.technokratos.utils.mapper.PracticeWorkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PracticeWorkServiceImpl implements PracticeWorkService {

    private final PracticeWorkRepository practiceWorkRepository;
    private final PracticeWorkMapper practiceWorkMapper;

    private final CourseRepository courseRepository;

    @Override
    public PracticeWorkResponse getPracticeWorkById(UUID courseId, UUID workId) {
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException();
        }
        if (!practiceWorkRepository.existsById(workId)) {
            throw new PracticeWorkNotFoundException();
        }
        return practiceWorkMapper.toPracticeWorkResponse(
                practiceWorkRepository.findPracticeWorkEntityByIdAndCourse_Id(workId, courseId)
                        .orElseThrow(PracticeWorkNotFoundException::new));
    }

    @Override
    public PageResponse<PracticeWorkResponse> getPracticeWorksWithPagination(UUID courseId, int page, int size) {
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException();
        }
        PageRequest pageRequest =  PageRequest.of(page, size);
        Page<PracticeWorkEntity> practiceWorkPage = practiceWorkRepository
                .findPracticeWorkEntitiesByCourse_Id(courseId, pageRequest);
        return PageResponse.<PracticeWorkResponse>builder()
                .content(practiceWorkMapper.toList(practiceWorkPage.getContent()))
                .totalPages(practiceWorkPage.getTotalPages())
                .totalElements(practiceWorkPage.getTotalElements())
                .build();
    }
}
