package com.technokratos.controllers;

import com.technokratos.api.FileApi;
import com.technokratos.dto.response.FileResponse;
import com.technokratos.model.FileEntity;
import com.technokratos.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FileController implements FileApi {

    private final FileService fileService;

    @Override
    public ResponseEntity<FileResponse> upload(MultipartFile file, String fileType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fileService.saveFile(file, fileType));
    }

    @Override
    public ResponseEntity<GridFsResource> downloadFile(UUID fileId) {
        FileEntity file = fileService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "filename=\"" + file.getOriginalFileName() + "\"")
                .body(fileService.getFileFromStorage(file.getObjectId()));
    }
}
