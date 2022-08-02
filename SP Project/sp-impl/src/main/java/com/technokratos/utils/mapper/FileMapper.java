package com.technokratos.utils.mapper;

import com.technokratos.dto.response.FileResponse;
import com.technokratos.model.FileEntity;
import com.technokratos.service.FileService;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = FileService.class)
public interface FileMapper {

    FileResponse toFileResponse(FileEntity file);


}
