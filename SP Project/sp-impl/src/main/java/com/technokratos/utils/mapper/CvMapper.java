package com.technokratos.utils.mapper;

import com.technokratos.dto.request.UpdateCvRequest;
import com.technokratos.dto.response.CvResponse;
import com.technokratos.model.CvEntity;
import com.technokratos.service.FileService;
import com.technokratos.service.SkillService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", uses = {SkillMapper.class, FileService.class, SkillService.class})
public interface CvMapper {

    @Mapping(target = "skills", ignore = true)
    @Mapping(source = "cvFileId", target = "file", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    CvEntity toCvEntity(UpdateCvRequest updateCvRequest);

    @Mapping(target = "skills", ignore = true)
    @Mapping(source = "cvFileId", target = "file", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void toCvEntity(UpdateCvRequest updateCvRequest, @MappingTarget CvEntity cv);

    @Mapping(source = "file", target = "cvFile")
    CvResponse toCvResponse(CvEntity cv);

}
