package com.technokratos.service;

import com.technokratos.dto.response.AcademyGroupResponse;
import com.technokratos.dto.response.PageResponse;

import java.util.UUID;

public interface AcademyGroupService {
    PageResponse<AcademyGroupResponse> getAcademyGroupByCourse(UUID courseId, int page, int size, String query);
}
