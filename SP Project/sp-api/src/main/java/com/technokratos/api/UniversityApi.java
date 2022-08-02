package com.technokratos.api;

import com.technokratos.dto.request.UniversityRequest;
import com.technokratos.dto.response.ExceptionResponse;
import com.technokratos.dto.response.PageResponse;
import com.technokratos.dto.response.SuccessResponse;
import com.technokratos.dto.response.UniversityResponse;
import com.technokratos.validation.ValidationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/universities")
public interface UniversityApi {

    @Operation(summary = "Add university")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "University added",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = UniversityResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Occupied university code or validation error",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class)
                            ),
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ValidationError.class))
                    })
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<UniversityResponse> addUniversity(@RequestBody @Valid UniversityRequest universityRequest);

    @Operation(summary = "Get university by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get university",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = UniversityResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "University not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class))
                    })
    })
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<UniversityResponse> getUniversityById(@PathVariable("id") UUID universityId);

    @Operation(summary = "Update university")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "University updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = UniversityResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Occupied university code or validation error",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class)
                            ),
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ValidationError.class))
                    })
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<UniversityResponse> updateUniversity(@RequestBody @Valid UniversityRequest universityRequest,
                                                        @PathVariable("id") UUID universityId);

    @Operation(summary = "Delete university by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "University deleted",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SuccessResponse.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "University not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class))
                    })
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessResponse> deleteUniversityById(@PathVariable("id") UUID universityId);

    @Operation(summary = "Get universities with pagination and entry query")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of universities",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema =
                                            @Schema(implementation = UniversityResponse.class))
                            )
                    })
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PageResponse<UniversityResponse>> getUniversities(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam("page") int page, @RequestParam("size") int size);

}
