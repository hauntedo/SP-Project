package com.technokratos.api;

import com.technokratos.dto.request.CourseRequest;
import com.technokratos.dto.response.*;
import com.technokratos.validation.ValidationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/courses")
public interface CourseApi {

    @Operation(summary = "Add course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course added",
                    content = {
                            @Content(mediaType = "application/json",
                                            schema =
                                            @Schema(implementation = CourseResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Occupied course code or code validation error",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class)
                            ),
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationError.class))
                    })
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<CourseResponse> addCourse(@RequestBody @Valid CourseRequest courseRequest);

    @Operation(summary = "Get course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get course",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = CourseResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Course not found",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))
            })
    })
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<CourseResponse> getCourseById(@PathVariable("id") UUID courseId);

    @Operation(summary = "Get all courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of courses",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema =
                                            @Schema(implementation = PageResponse.class))
                            )
                    })
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PageResponse<CourseResponse>> getCourses(@RequestParam("page") int page,
                                                            @RequestParam("size") int size);

    @Operation(summary = "Update course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = CourseResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Occupied course code or code validation error",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class)
                            ),
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ValidationError.class))
                    })
    })
    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<CourseResponse> updateCourse(@PathVariable("id") UUID courseId,
                                                @RequestBody @Valid CourseRequest courseRequest);

    @Operation(summary = "Delete course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course deleted",
                    content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SuccessResponse.class))
                    }
                    ),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class))
                    })
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<SuccessResponse> deleteCourse(@PathVariable("id") UUID courseId);

    @Operation(summary = "Get practice work by course id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Practice work",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PracticeWorkResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Course or practice work not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class))
                    })
    })
    @GetMapping(value = "/{course_id}/works/{work_id}")
    ResponseEntity<PracticeWorkResponse> getPracticeWorkByCourseId(@PathVariable("course_id") UUID courseId,
                                                                       @PathVariable("work_id") UUID workId);

    @Operation(summary = "Get practice works with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of practice works",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema =
                                            @Schema(implementation = PageResponse.class))
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class))
                    })
    })
    @GetMapping(value = "/{course_id}/works")
    ResponseEntity<PageResponse<PracticeWorkResponse>> getPracticeWorksByCourseId(@PathVariable("course_id") UUID courseId,
                                                                          @RequestParam("page") int page,
                                                                                  @RequestParam("size") int size);


    @Operation(summary = "Get courses by university with pagination and sorting by param")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of courses",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema =
                                            @Schema(implementation = CourseResponse.class))
                            )
                    })
    })
    @GetMapping(value = "/{university_id}/university", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PageResponse<CourseResponse>> getCoursesByUniversity(@PathVariable("university_id") UUID universityId,
                                                                        @RequestParam("size") int size,
                                                                        @RequestParam("page") int page,
                                                                        @RequestParam(value = "param", required = false) String param);

    @Operation(summary = "Get available courses for user with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of courses",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema =
                                            @Schema(implementation = CourseResponse.class))
                            )
                    })
    })
    @GetMapping(value = "/{user_id}/available", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PageResponse<CourseResponse>> getAvailableCoursesForUser(
            @PathVariable("user_id") UUID userId,
            @RequestParam("size") int size,
            @RequestParam("page") int page);
}
