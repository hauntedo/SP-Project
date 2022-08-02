package com.technokratos.service.impl;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.technokratos.dto.response.FileResponse;
import com.technokratos.exception.badrequest.BadFileException;
import com.technokratos.exception.internalserver.FileSaveFailureException;
import com.technokratos.exception.notfound.FileNotFoundException;
import com.technokratos.model.FileEntity;
import com.technokratos.repository.FileRepository;
import com.technokratos.service.FileService;
import com.technokratos.utils.enums.FileType;
import com.technokratos.utils.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperations;
    private final FileMapper fileMapper;

    @Override
    public FileResponse saveFile(MultipartFile file, String fileType) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String objectId = null;

        try {
            objectId = saveBsonFileInStorage(file.getInputStream(), fileName);

        FileEntity fileEntity = FileEntity.builder()
                .contentType(file.getContentType())
                .originalFileName(fileName)
                .size(file.getSize())
                .objectId(objectId)
                .fileType(FileType.valueOf(fileType))
                .build();

        return fileMapper.toFileResponse(fileRepository.save(fileEntity));

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new BadFileException(e.getMessage());
        } catch (IOException e) {
            throw new FileSaveFailureException("Error saving file: " + fileName);
        }
    }

    @Override
    public FileEntity getFile(UUID fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(FileNotFoundException::new);
    }

    @Override
    public GridFsResource getFileFromStorage(String objectId) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(objectId)));
        if (gridFSFile == null) {
            throw new FileNotFoundException();
        }
        return gridFsOperations.getResource(gridFSFile);
    }

    private String saveBsonFileInStorage(InputStream inputStream, String fileName) {
        return gridFsTemplate.store(inputStream, fileName).toString();
    }

}
