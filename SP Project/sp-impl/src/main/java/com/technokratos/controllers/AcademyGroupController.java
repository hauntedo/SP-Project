package com.technokratos.controllers;

import com.technokratos.api.AcademyGroupApi;
import com.technokratos.dto.response.AcademyGroupResponse;
import com.technokratos.dto.response.PageResponse;
import com.technokratos.service.AcademyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AcademyGroupController implements AcademyGroupApi {

    private final AcademyGroupService academyGroupService;

    @Override
    public ResponseEntity<PageResponse<AcademyGroupResponse>> getAcademyGroupsByCourse(UUID courseId, int page, int size, String query) {
        return ResponseEntity.ok(academyGroupService.getAcademyGroupByCourse(courseId, page, size, query));
    }
}
