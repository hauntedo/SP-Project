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
@Schema(name = "university", description = "info about university")
public class UniversityResponse {

    @JsonProperty("id")
    @Schema(name = "university id")
    private UUID id;

    @JsonProperty("name")
    @Schema(name = "university name", example = "ИТИС")
    private String name;

    @JsonProperty("code")
    @Schema(name = "university code", example = "ITIS")
    private String code;
}
