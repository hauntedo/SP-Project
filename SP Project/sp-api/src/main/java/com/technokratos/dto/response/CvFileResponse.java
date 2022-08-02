package com.technokratos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "cv file", description = "info about cv file")
public class CvFileResponse {

    @JsonProperty("filename")
    @Schema(name = "filename", example = "CV_John_Doe.pdf")
    private String fileName;

    @JsonProperty("file_size")
    @Schema(name = "file size", example = "123123")
    private Long fileSize;

}
