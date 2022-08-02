package com.technokratos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "form for update user cv")
public class UpdateCvRequest {

    @JsonProperty("file_id")
    @Schema(name = "cv file")
    private UUID cvFileId;

    @JsonProperty("skills")
    @Schema(name = "skills", description = "info about skills that user has")
    private Set<UUID> skills = new HashSet<>();

    @JsonProperty("portfolio_links")
    @Schema(name = "portfolio links")
    private String portfolioLinks;

    @JsonProperty("experience_description")
    @Schema(name = "experience description")
    private String experienceDescription;
}
