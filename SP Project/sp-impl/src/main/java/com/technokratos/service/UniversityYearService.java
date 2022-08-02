package com.technokratos.service;

import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.UniversityYearResponse;

import java.util.UUID;

public interface UniversityYearService {
    PageResponse<UniversityYearResponse> getUniversityYearsByUniversity(UUID universityId, int page, int size);
}
