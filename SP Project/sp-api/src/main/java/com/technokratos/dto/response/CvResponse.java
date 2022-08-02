package com.technokratos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "cv", description = "info about user cv")
public class CvResponse {

    @JsonProperty("cv_file")
    @Schema(name = "cv file")
    private FileResponse cvFile;

    @JsonProperty("skills")
    @Schema(name = "skills", description = "info about skills that user has")
    private Set<SkillResponse> skills = new HashSet<>();

    @JsonProperty("portfolio_links")
    @Schema(name = "portfolio links")
    private String portfolioLinks;

    @JsonProperty("experience_description")
    @Schema(name = "experience description")
    private String experienceDescription;
}
