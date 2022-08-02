package com.technokratos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.technokratos.validation.NonWhiteSpace;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "request for add university")
public class UniversityRequest {

    @JsonProperty("name")
    @NotBlank(message = "Shouldn't be blank")
    @Schema(description = "university name")
    private String name;

    @JsonProperty("code")
    @NonWhiteSpace
    @NotBlank(message = "Shouldn't be blank")
    @Schema(description = "university code")
    private String code;
}
