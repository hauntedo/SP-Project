package com.technokratos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(name = "university year", description = "info about university year")
public class UniversityYearResponse {

    @JsonProperty("university_year")
    @Schema(name = "university year value", example = "2")
    private Integer universityYear;

    @JsonProperty("university")
    @Schema(name = "university", description = "info about university")
    private UniversityResponse university;

}
