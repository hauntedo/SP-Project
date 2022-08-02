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
@Data
@Builder
@Schema(name = "course", description = "info about course")
public class CourseResponse {

    @JsonProperty("id")
    @Schema(name = "course id")
    private UUID id;

    @JsonProperty("name")
    @Schema(name = "course name", example = "Java development")
    private String name;

    @JsonProperty("code")
    @Schema(name = "course code", example = "JAVA_DEVELOPING")
    private String code;

    @JsonProperty("description")
    @Schema(name = "course description")
    private String description;

    @JsonProperty("state")
    @Schema(name = "course state", example = "DELETED")
    private String state;


}
