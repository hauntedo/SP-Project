package com.technokratos.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.technokratos.validation.NonWhiteSpace;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "form for create courses")
public class CourseRequest {

    @NotBlank
    @JsonProperty("name")
    @Schema(name = "course name", example = "Java")
    private String name;

    @NotBlank
    @JsonProperty("code")
    @NonWhiteSpace
    @Schema(name = "code", example = "JAVA_DEVELOPING")
    private String code;

    @NotBlank
    @JsonProperty("description")
    @Schema(name = "description", description = "Study java")
    private String description;

}
