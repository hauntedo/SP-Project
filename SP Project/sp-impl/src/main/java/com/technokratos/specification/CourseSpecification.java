package com.technokratos.specification;

import com.technokratos.model.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.UUID;

public class CourseSpecification {

    public static Specification<CourseEntity> hasCourseByUserId(UUID userId) {
        return (root, query, criteriaBuilder) -> {
            Join<CourseEntity, UniversityEntity> courseUniversityJoin = root.join("university");
            Join<UniversityEntity, UniversityYearEntity> universityUniversityYearJoin =
                    courseUniversityJoin.join("universityYearEntities");
            Join<UniversityYearEntity, AcademyGroupEntity> universityYearGroupJoin = universityUniversityYearJoin.join("academyGroups");
            Join<AcademyGroupEntity, UserEntity> academyGroupUserJoin = universityYearGroupJoin.join("users");
            return criteriaBuilder.equal(academyGroupUserJoin.get("id"), userId);
        };
    }


}
