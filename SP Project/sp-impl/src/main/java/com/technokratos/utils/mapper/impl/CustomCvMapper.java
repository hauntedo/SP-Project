package com.technokratos.utils.mapper.impl;

import com.technokratos.dto.request.UpdateCvRequest;
import com.technokratos.dto.response.CvResponse;
import com.technokratos.dto.response.FileResponse;
import com.technokratos.dto.response.SkillResponse;
import com.technokratos.exception.badrequest.BadFileException;
import com.technokratos.model.CvEntity;
import com.technokratos.model.FileEntity;
import com.technokratos.model.SkillEntity;
import com.technokratos.service.FileService;
import com.technokratos.service.SkillService;
import com.technokratos.utils.enums.FileType;
import com.technokratos.utils.mapper.CvMapper;
import com.technokratos.utils.mapper.SkillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomCvMapper implements CvMapper {

    private final SkillMapper skillMapper;

    private final FileService fileService;

    private final SkillService skillService;

    @Autowired
    public CustomCvMapper(SkillMapper skillMapper, FileService fileService, SkillService skillService) {
        this.skillMapper = skillMapper;
        this.fileService = fileService;
        this.skillService = skillService;
    }

    @Override
    public CvEntity toCvEntity(UpdateCvRequest updateCvRequest) {
        if ( updateCvRequest == null ) {
            return null;
        }

        CvEntity.CvEntityBuilder<?, ?> cvEntity = CvEntity.builder();

        if ( updateCvRequest.getCvFileId() != null ) {
            FileEntity file = fileService.getFile( updateCvRequest.getCvFileId() );
            if (file.getFileType() != FileType.CV_FILE) {
                throw new BadFileException("Incorrect file type");
            }
            cvEntity.file(file);
        }
        if (updateCvRequest.getSkills() != null &&
                updateCvRequest.getPortfolioLinks() != null &&
                updateCvRequest.getExperienceDescription() != null) {
            cvEntity.experienceDescription( updateCvRequest.getExperienceDescription() );
            cvEntity.portfolioLinks( updateCvRequest.getPortfolioLinks() );
            cvEntity.skills( skillService.getSkillsBySkillIdSet(updateCvRequest.getSkills()) );
        }
        return cvEntity.build();
    }

    @Override
    public void toCvEntity(UpdateCvRequest updateCvRequest, CvEntity cv) {
        if ( updateCvRequest == null ) {
            return;
        }
        if (updateCvRequest.getSkills() != null &&
                updateCvRequest.getPortfolioLinks() != null &&
                updateCvRequest.getExperienceDescription() != null) {
            cv.setPortfolioLinks( updateCvRequest.getPortfolioLinks() );
            cv.setExperienceDescription( updateCvRequest.getExperienceDescription() );
        }
        if ( updateCvRequest.getCvFileId() != null ) {
            FileEntity file = fileService.getFile( updateCvRequest.getCvFileId() );
            if (file.getFileType() != FileType.CV_FILE) {
                throw new BadFileException("Incorrect file type");
            }
            cv.setFile(file);
        }
    }

    @Override
    public CvResponse toCvResponse(CvEntity cv) {
        if ( cv == null ) {
            return null;
        }

        CvResponse.CvResponseBuilder cvResponse = CvResponse.builder();

        cvResponse.cvFile( fileEntityToFileResponse( cv.getFile() ) );
        cvResponse.skills( skillEntitySetToSkillResponseSet( cv.getSkills() ) );
        cvResponse.portfolioLinks( cv.getPortfolioLinks() );
        cvResponse.experienceDescription( cv.getExperienceDescription() );

        return cvResponse.build();
    }

    protected FileResponse fileEntityToFileResponse(FileEntity fileEntity) {
        if ( fileEntity == null ) {
            return null;
        }

        FileResponse.FileResponseBuilder fileResponse = FileResponse.builder();

        fileResponse.id( fileEntity.getId() );
        fileResponse.originalFileName( fileEntity.getOriginalFileName() );
        fileResponse.contentType( fileEntity.getContentType() );
        if ( fileEntity.getSize() != null ) {
            fileResponse.size( fileEntity.getSize() );
        }
        if ( fileEntity.getFileType() != null ) {
            fileResponse.fileType( fileEntity.getFileType().name() );
        }

        return fileResponse.build();
    }

    protected SkillResponse skillEntityToSkillResponse(SkillEntity skillEntity) {
        if ( skillEntity == null ) {
            return null;
        }

        SkillResponse.SkillResponseBuilder skillResponse = SkillResponse.builder();

        skillResponse.name( skillEntity.getName() );

        return skillResponse.build();
    }

    protected Set<SkillResponse> skillEntitySetToSkillResponseSet(Set<SkillEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<SkillResponse> set1 = new HashSet<SkillResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( SkillEntity skillEntity : set ) {
            set1.add( skillEntityToSkillResponse( skillEntity ) );
        }

        return set1;
    }
}
