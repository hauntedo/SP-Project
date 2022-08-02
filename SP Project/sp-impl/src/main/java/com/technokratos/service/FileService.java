package com.technokratos.service;

import com.technokratos.dto.response.FileResponse;
import com.technokratos.model.FileEntity;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileService {

    FileResponse saveFile(MultipartFile file, String fileType);

    FileEntity getFile(UUID id);

    GridFsResource getFileFromStorage(String objectId);

}
