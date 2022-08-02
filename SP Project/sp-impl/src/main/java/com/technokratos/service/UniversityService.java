package com.technokratos.service;

import com.technokratos.dto.request.UniversityRequest;
import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.UniversityResponse;

import java.util.UUID;

public interface UniversityService {
    UniversityResponse addUniversity(UniversityRequest universityRequest);

    UniversityResponse getUniversityById(UUID universityId);

    UniversityResponse updateUniversity(UUID universityId, UniversityRequest universityRequest);

    void deleteUniversityById(UUID universityId);

    PageResponse<UniversityResponse> getUniversities(String query, int page, int size);

}
