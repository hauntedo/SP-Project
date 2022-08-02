package com.technokratos.utils.mapper;

import com.technokratos.dto.request.CourseRequest;
import com.technokratos.dto.response.CourseResponse;
import com.technokratos.model.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "courseState", target = "state")
    CourseResponse toCourseResponse(CourseEntity courseEntity);

    CourseEntity toCourseEntity(CourseRequest courseRequest);

    List<CourseResponse> toCourseResponse(Set<CourseEntity> courses);

    List<CourseResponse> toCourseResponse(List<CourseEntity> courses);

    void updateCourse(CourseRequest request, @MappingTarget CourseEntity course);
}
