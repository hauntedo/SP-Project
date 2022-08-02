package com.technokratos.service.impl;

import com.technokratos.dto.response.AcademyGroupResponse;
import com.technokratos.dto.response.PageResponse;
import com.technokratos.model.AcademyGroupEntity;
import com.technokratos.repository.AcademyGroupRepository;
import com.technokratos.service.AcademyGroupService;
import com.technokratos.specification.AcademyGroupSpecification;
import com.technokratos.utils.mapper.AcademyGroupMapper;
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
public class AcademyGroupServiceImpl implements AcademyGroupService {

    private final AcademyGroupRepository academyGroupRepository;
    private final AcademyGroupMapper academyGroupMapper;
    @Override
    public PageResponse<AcademyGroupResponse> getAcademyGroupByCourse(UUID courseId, int page, int size, String query) {
        Specification<AcademyGroupEntity> specification = AcademyGroupSpecification
                .hasCourseById(courseId)
                .and(AcademyGroupSpecification.byName(query));
        PageRequest pageRequest = PageRequest.of(page, size);
        log.info("Find academy group by course with query value: {}", query);
        Page<AcademyGroupEntity> result = academyGroupRepository
                    .findAll(specification, pageRequest);
        return PageResponse.<AcademyGroupResponse>builder()
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .content(academyGroupMapper.toList(result.getContent()))
                .build();
    }
}
