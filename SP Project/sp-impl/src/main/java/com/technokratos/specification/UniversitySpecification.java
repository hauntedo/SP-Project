package com.technokratos.specification;

import com.technokratos.model.UniversityEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;

public class UniversitySpecification {

    public static Specification<UniversityEntity> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            Path<String> path = root.get("name");
            return criteriaBuilder.like(path, "%" + name.toLowerCase() + "%");
        };
    }
}
