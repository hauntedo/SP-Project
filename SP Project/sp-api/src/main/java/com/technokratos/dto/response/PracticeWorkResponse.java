package com.technokratos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "practice work", description = "info about practice work")
public class PracticeWorkResponse {

    @JsonProperty("id")
    @Schema(name = "id")
    private UUID id;

    @JsonProperty("file")
    @Schema(name = "file", description = "info about attached file")
    private FileResponse fileResponse;

    @JsonProperty("title")
    @Schema(name = "title")
    private String title;

    @JsonProperty("general_info")
    @Schema(name = "general info")
    private String generalInfo;

    @JsonProperty("technical_requirement")
    @Schema(name = "technical requirement")
    private String technicalRequirement;
}
