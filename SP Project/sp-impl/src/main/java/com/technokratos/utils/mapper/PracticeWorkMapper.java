package com.technokratos.utils.mapper;

import com.technokratos.dto.response.PracticeWorkResponse;
import com.technokratos.model.PracticeWorkEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PracticeWorkMapper {

    PracticeWorkResponse toPracticeWorkResponse(PracticeWorkEntity practiceWork);

    List<PracticeWorkResponse> toList(List<PracticeWorkEntity> practiceWorks);




}
