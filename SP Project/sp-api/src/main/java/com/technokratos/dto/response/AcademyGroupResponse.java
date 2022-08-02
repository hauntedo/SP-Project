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
@Schema(name = "academy group", description = "info about academy group")
public class AcademyGroupResponse {

    @JsonProperty("id")
    @Schema(name = "academy group id")
    private UUID id;

    @JsonProperty("name")
    @Schema(name = "academy group name", example = "11-001")
    private String name;

    @JsonProperty("university_year")
    @Schema(name = "university year", description = "info about university year")
    private UniversityYearResponse universityYear;

}
