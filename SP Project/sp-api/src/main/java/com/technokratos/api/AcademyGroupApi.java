package com.technokratos.api;

import com.technokratos.dto.response.AcademyGroupResponse;
import com.technokratos.dto.response.PageResponse;
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

@RequestMapping("/api/academy-groups")
public interface AcademyGroupApi {

    @Operation(summary = "Get academy groups by course with pagination and entry query")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of academy groups",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema =
                                            @Schema(implementation = AcademyGroupResponse.class))
                            )
                    })
    })
    @GetMapping(value = "/{course_id}/academy-groups", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PageResponse<AcademyGroupResponse>> getAcademyGroupsByCourse(
            @PathVariable("course_id") UUID courseId,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "query", required = false) String query);
}
