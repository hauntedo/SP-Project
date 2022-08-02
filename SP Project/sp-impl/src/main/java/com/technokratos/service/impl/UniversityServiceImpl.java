package com.technokratos.service.impl;

import com.technokratos.dto.request.UniversityRequest;
import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.UniversityResponse;
import com.technokratos.exception.badrequest.OccupiedDataException;
import com.technokratos.exception.notfound.UniversityNotFoundException;
import com.technokratos.model.UniversityEntity;
import com.technokratos.repository.UniversityRepository;
import com.technokratos.service.UniversityService;
import com.technokratos.specification.UniversitySpecification;
import com.technokratos.utils.enums.UniversityState;
import com.technokratos.utils.mapper.UniversityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;
    private final UniversityMapper universityMapper;

    @Override
    public UniversityResponse addUniversity(UniversityRequest universityRequest) {
        if (universityRepository.existsByCode(universityRequest.getCode())) {
            log.error("University exist: {}", universityRequest.getCode());
            throw new OccupiedDataException("University code is occupied: " + universityRequest.getCode());
        }
        UniversityEntity universityEntity = universityMapper.toUniversityEntity(universityRequest);
        universityEntity.setUniversityState(UniversityState.NOT_DELETED);
        log.info("Save university entity: {}", universityEntity);
        return universityMapper.toUniversityResponse(universityRepository.save(universityEntity));
    }

    @Override
    public UniversityResponse getUniversityById(UUID universityId) {
        log.info("Find university by ID from database: {}", universityId);
        UniversityEntity university = universityRepository.findById(universityId)
                .orElseThrow(UniversityNotFoundException::new);
        return universityMapper.toUniversityResponse(university);
    }

    @Override
    public UniversityResponse updateUniversity(UUID universityId, UniversityRequest universityRequest) {
        if (universityRepository.existsByCode(universityRequest.getCode())) {
            log.error("University exist: {}", universityRequest.getCode());
            throw new OccupiedDataException("University code is occupied: " + universityRequest.getCode());
        }
        log.info("Find university by ID from database: {}", universityId);
        UniversityEntity university = universityRepository.findById(universityId)
                .orElseThrow(UniversityNotFoundException::new);
        universityMapper.updateUniversity(university, universityRequest);
        log.info("Save university entity: {}", university);
        return universityMapper.toUniversityResponse(universityRepository.save(university));
    }

    @Override
    public void deleteUniversityById(UUID universityId) {
        log.info("Delete university by ID from database: {}", universityId);
        UniversityEntity university = universityRepository.findById(universityId)
                        .orElseThrow(UniversityNotFoundException::new);
        universityRepository.delete(university);
    }

    @Override
    public PageResponse<UniversityResponse> getUniversities(String query, int page, int size) {
        Specification<UniversityEntity> specification = UniversitySpecification.byName(query);
        PageRequest pageRequest = PageRequest.of(page, size);
        log.info("Find universities by name {}", query);
        Page<UniversityEntity> result = universityRepository.findAll(specification, pageRequest);
        return PageResponse.<UniversityResponse>builder()
                .content(universityMapper.toList(result.getContent()))
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .build();
    }
}
