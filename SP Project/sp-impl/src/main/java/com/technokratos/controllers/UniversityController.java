package com.technokratos.controllers;

import com.technokratos.api.UniversityApi;
import com.technokratos.dto.request.UniversityRequest;
import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.SuccessResponse;
import com.technokratos.dto.response.UniversityResponse;
import com.technokratos.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UniversityController implements UniversityApi {

    private final UniversityService universityService;

    @Override
    public ResponseEntity<UniversityResponse> addUniversity(UniversityRequest universityRequest) {
        return ResponseEntity.status(201)
                .body(universityService.addUniversity(universityRequest));
    }

    @Override
    public ResponseEntity<UniversityResponse> getUniversityById(UUID universityId) {
        return ResponseEntity.ok(universityService.getUniversityById(universityId));
    }

    @Override
    public ResponseEntity<UniversityResponse> updateUniversity(UniversityRequest universityRequest, UUID universityId) {
        return ResponseEntity.status(201)
                .body(universityService.updateUniversity(universityId, universityRequest));
    }

    @Override
    public ResponseEntity<SuccessResponse> deleteUniversityById(UUID universityId) {
        universityService.deleteUniversityById(universityId);
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("University deleted successfully")
                        .time(Instant.now())
                        .build()
        );
    }

    @Override
    public ResponseEntity<PageResponse<UniversityResponse>> getUniversities(String query, int page, int size) {
        return ResponseEntity.ok(universityService.getUniversities(query, page, size));
    }
}
