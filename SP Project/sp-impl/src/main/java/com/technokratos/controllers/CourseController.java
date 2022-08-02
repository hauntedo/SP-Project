package com.technokratos.controllers;

import com.technokratos.api.CourseApi;
import com.technokratos.dto.request.CourseRequest;
import com.technokratos.dto.response.*;
import com.technokratos.service.CourseService;
import com.technokratos.service.PracticeWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CourseController implements CourseApi {

    private final CourseService courseService;
    private final PracticeWorkService practiceWorkService;

    @Override
    public ResponseEntity<CourseResponse> addCourse(CourseRequest courseRequest) {
        return ResponseEntity.status(201)
                .body(courseService.createCourse(courseRequest));
    }

    @Override
    public ResponseEntity<CourseResponse> getCourseById(UUID courseId) {
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    @Override
    public ResponseEntity<PageResponse<CourseResponse>> getCourses(int page, int size) {
        return ResponseEntity.ok(courseService.getCourses(page,size));
    }

    @Override
    public ResponseEntity<CourseResponse> updateCourse(UUID courseId, CourseRequest courseRequest) {
        return ResponseEntity.status(201)
                .body(courseService.updateCourseById(courseId, courseRequest));
    }

    @Override
    public ResponseEntity<SuccessResponse> deleteCourse(UUID courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("Course deleted successfully")
                        .time(Instant.now())
                        .build()
        );
    }

    @Override
    public ResponseEntity<PracticeWorkResponse> getPracticeWorkByCourseId(UUID courseId, UUID workId) {
        return ResponseEntity.ok(practiceWorkService.getPracticeWorkById(courseId, workId));
    }

    @Override
    public ResponseEntity<PageResponse<PracticeWorkResponse>> getPracticeWorksByCourseId(UUID courseId,
                                                                                         int page, int size) {
        return ResponseEntity.ok(practiceWorkService.getPracticeWorksWithPagination(courseId, page, size));
    }

    @Override
    public ResponseEntity<PageResponse<CourseResponse>> getCoursesByUniversity(UUID universityId, int size, int page, String param) {
        return ResponseEntity.ok(courseService.getCoursesByUniversity(universityId, size, page, param));
    }

    @Override
    public ResponseEntity<PageResponse<CourseResponse>> getAvailableCoursesForUser(UUID userId, int size, int page) {
        return ResponseEntity.ok(courseService.getAvailableCoursesForUser(userId, size, page));
    }
}
