package com.technokratos.service;

import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.PracticeWorkResponse;

import java.util.UUID;

public interface PracticeWorkService {
    PracticeWorkResponse getPracticeWorkById(UUID courseId, UUID workId);

    PageResponse<PracticeWorkResponse> getPracticeWorksWithPagination(UUID courseId, int page, int size);
}
