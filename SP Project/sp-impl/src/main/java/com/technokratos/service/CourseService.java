package com.technokratos.service;

import com.technokratos.dto.request.CourseRequest;
import com.technokratos.dto.response.CourseResponse;
import com.technokratos.dto.response.PageResponse;

import java.util.UUID;

public interface CourseService {

    CourseResponse createCourse(CourseRequest courseRequest);

    CourseResponse getCourse(UUID courseId);

    PageResponse<CourseResponse> getCourses(int page, int size);

    CourseResponse updateCourseById(UUID courseId, CourseRequest courseRequest);

    void deleteCourseById(UUID courseId);

    PageResponse<CourseResponse> getUserCourses(UUID userId, int page, int size);

    PageResponse<CourseResponse> getCoursesByUniversity(UUID universityId, int size, int page, String param);

    PageResponse<CourseResponse> getAvailableCoursesForUser(UUID userId, int size, int page);
}
