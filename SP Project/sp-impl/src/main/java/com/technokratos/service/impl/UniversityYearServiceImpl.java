package com.technokratos.service.impl;

import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.UniversityYearResponse;
import com.technokratos.model.UniversityYearEntity;
import com.technokratos.repository.UniversityYearRepository;
import com.technokratos.service.UniversityYearService;
import com.technokratos.utils.mapper.UniversityYearMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UniversityYearServiceImpl implements UniversityYearService {

    private final UniversityYearRepository universityYearRepository;
    private final UniversityYearMapper universityYearMapper;

    @Override
    public PageResponse<UniversityYearResponse> getUniversityYearsByUniversity(UUID universityId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        log.info("Find university years by university id {}", universityId);
        Page<UniversityYearEntity> result = universityYearRepository
                .findUniversityYearEntitiesByUniversity_Id(universityId, pageRequest);
        return PageResponse.<UniversityYearResponse>builder()
                .content(universityYearMapper.toList(result.getContent()))
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();
    }
}
