package com.technokratos.controllers;

import com.technokratos.api.UniversityYearApi;
import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.UniversityYearResponse;
import com.technokratos.service.UniversityYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UniversityYearController implements UniversityYearApi {

    private final UniversityYearService universityYearService;

    @Override
    public ResponseEntity<PageResponse<UniversityYearResponse>> getUniversityYearsByUniversity(UUID universityId, int page, int size) {
        return ResponseEntity.ok(universityYearService.getUniversityYearsByUniversity(universityId, page, size));
    }
}
