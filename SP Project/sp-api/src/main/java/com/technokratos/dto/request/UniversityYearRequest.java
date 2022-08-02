package com.technokratos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "form for add university year")
public class UniversityYearRequest {

    @JsonProperty("university_year")
    @Positive(message = "Should be positive")
    @Schema(description = "university year")
    private int universityYear;

    @NotBlank(message = "Should not be blank")
    @JsonProperty("university_id")
    @Schema(description = "university id")
    private UUID universityId;

}
