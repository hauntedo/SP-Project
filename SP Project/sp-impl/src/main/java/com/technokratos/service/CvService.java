package com.technokratos.service;

import com.technokratos.dto.request.UpdateCvRequest;
import com.technokratos.dto.response.CvResponse;

import java.util.UUID;

public interface CvService {
    CvResponse updateCvInfo(UUID userId, UpdateCvRequest request);

}
