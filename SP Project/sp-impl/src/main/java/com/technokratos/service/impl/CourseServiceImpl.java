package com.technokratos.service.impl;

import com.technokratos.dto.request.CourseRequest;
import com.technokratos.dto.response.CourseResponse;
import com.technokratos.dto.response.PageResponse;
import com.technokratos.exception.badrequest.OccupiedDataException;
import com.technokratos.exception.notfound.CourseNotFoundException;
import com.technokratos.exception.notfound.UserNotFoundException;
import com.technokratos.model.CourseEntity;
import com.technokratos.repository.CourseRepository;
import com.technokratos.repository.UserRepository;
import com.technokratos.service.CourseService;
import com.technokratos.specification.CourseSpecification;
import com.technokratos.utils.enums.CourseState;
import com.technokratos.utils.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final UserRepository userRepository;

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        if (courseRepository.existsCourseEntityByCode(courseRequest.getCode())) {
            throw new OccupiedDataException("Course code is occupied: " + courseRequest.getCode());
        } else {
            CourseEntity newCourse = courseMapper.toCourseEntity(courseRequest);
            newCourse.setCourseState(CourseState.NOT_DELETED);
            return courseMapper.toCourseResponse(courseRepository.save(newCourse));
        }
    }

    @Override
    public CourseResponse getCourse(UUID courseId) {
        return courseMapper.toCourseResponse(courseRepository.findById(courseId)
                .orElseThrow(CourseNotFoundException::new));
    }

    @Override
    public PageResponse<CourseResponse> getCourses(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CourseEntity> courses = courseRepository.findAllCourses(pageRequest);
        return PageResponse.<CourseResponse>builder()
                .content(courseMapper.toCourseResponse(courses.getContent()))
                .totalPages(courses.getTotalPages())
                .totalElements(courses.getTotalElements())
                .build();
    }

    @Override
    public PageResponse<CourseResponse> getUserCourses(UUID userId, int page, int size) {
        if (userRepository.existsUserEntityById(userId)) {
            throw new UserNotFoundException();
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CourseEntity> courses = courseRepository.findByUsers_Id(userId, pageRequest);
        return PageResponse.<CourseResponse>builder()
                .content(courseMapper.toCourseResponse(courses.getContent()))
                .totalPages(courses.getTotalPages())
                .totalElements(courses.getTotalElements())
                .build();
    }

    @Override
    public PageResponse<CourseResponse> getCoursesByUniversity(UUID universityId, int size, int page, String param) {
        Sort sort = Sort.by(param);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        log.info("Find courses by param '{}' by university '{}'", param, universityId);
        Page<CourseEntity> result = courseRepository.findCourseEntitiesByUniversity_Id(universityId, pageRequest);
        return PageResponse.<CourseResponse>builder()
                .content(courseMapper.toCourseResponse(result.getContent()))
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();
    }

    @Override
    public PageResponse<CourseResponse> getAvailableCoursesForUser(UUID userId, int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Specification<CourseEntity> specification = CourseSpecification.hasCourseByUserId(userId);
        log.info("Find available courses for user '{}'", userId);
        Page<CourseEntity> result = courseRepository.findAll(specification, pageRequest);
        return PageResponse.<CourseResponse>builder()
                .content(courseMapper.toCourseResponse(result.getContent()))
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .build();
    }

    @Override
    public CourseResponse updateCourseById(UUID courseId, CourseRequest courseRequest) {
        if (courseRepository.existsCourseEntityByCode(courseRequest.getCode())) {
            throw new OccupiedDataException("Course code is occupied: " + courseRequest.getCode());
        }
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFoundException::new);
        courseMapper.updateCourse(courseRequest, course);
        return courseMapper.toCourseResponse(courseRepository.save(course));
    }

    @Override
    public void deleteCourseById(UUID courseId) {
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFoundException::new);
        courseRepository.delete(course);
    }
}
