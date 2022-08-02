package com.technokratos.model;

import com.technokratos.utils.enums.CourseState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@SQLDelete(sql = "update course set course_state = 'DELETED' where id = ?")
@Where(clause = "course_state != 'DELETED'")
@Table(name = "course")
public class CourseEntity extends AbstractEntity {

    @Column(nullable = false, name = "course_name")
    private String name;

    @Column(unique = true, nullable = false, name = "course_code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private UniversityEntity university;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_state")
    private CourseState courseState;

    @ManyToMany(mappedBy = "courses")
    private Set<UserEntity> users = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private List<PracticeWorkEntity> practiceWorks;

}
