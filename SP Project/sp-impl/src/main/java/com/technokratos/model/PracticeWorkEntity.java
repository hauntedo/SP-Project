package com.technokratos.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "practice_work")
public class PracticeWorkEntity extends AbstractEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "general_info")
    private String generalInfo;

    @Column(name = "technical_requirement")
    private String technicalRequirement;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

}
