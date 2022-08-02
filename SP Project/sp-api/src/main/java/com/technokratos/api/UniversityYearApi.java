package com.technokratos.api;

import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.UniversityYearResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/api/university-years")
public interface UniversityYearApi {

    @Operation(summary = "Get university years by university with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of university years",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema =
                                            @Schema(implementation = UniversityYearResponse.class))
                            )
                    })
    })
    @GetMapping(value = "/{university_id}/university-years", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PageResponse<UniversityYearResponse>> getUniversityYearsByUniversity(
            @PathVariable("university_id") UUID universityId,
            @RequestParam("page") int page,
            @RequestParam("size") int size);

}
