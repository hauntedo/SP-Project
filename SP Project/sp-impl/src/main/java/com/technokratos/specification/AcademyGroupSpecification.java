package com.technokratos.specification;

import com.technokratos.model.AcademyGroupEntity;
import com.technokratos.model.CourseEntity;
import com.technokratos.model.UniversityEntity;
import com.technokratos.model.UniversityYearEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import java.util.UUID;

public class AcademyGroupSpecification {

    public static Specification<AcademyGroupEntity> hasCourseById(UUID courseId) {
        return (root, query, criteriaBuilder) -> {
            Join<AcademyGroupEntity, UniversityYearEntity> groupYearJoin = root.join("universityYear");
            Join<UniversityYearEntity, UniversityEntity> yearUniversityJoin = groupYearJoin.join("university");
            Join<UniversityEntity, CourseEntity> universityCourseJoin = yearUniversityJoin.join("courses");
            return criteriaBuilder.equal(universityCourseJoin.get("id"), courseId);
        };
    }

    public static Specification<AcademyGroupEntity> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            Path<String> path = root.get("name");
            return criteriaBuilder.like(path, "%" + name.toLowerCase() + "%");
        };
    }

}
