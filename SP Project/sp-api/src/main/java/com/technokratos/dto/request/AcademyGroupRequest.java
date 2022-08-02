package com.technokratos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "form for add academy group")
public class AcademyGroupRequest {

    @JsonProperty("name")
    @NotBlank(message = "Should not be blank")
    @Schema(description = "academy group name")
    private String name;

    @NotBlank(message = "Should not be blank")
    @JsonProperty("university_year_id")
    @Schema(description = "university year id")
    private UUID universityYearId;
}
