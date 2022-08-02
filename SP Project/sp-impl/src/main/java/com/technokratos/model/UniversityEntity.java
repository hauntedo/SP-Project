package com.technokratos.model;

import com.technokratos.utils.enums.UniversityState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@SQLDelete(sql = "update university set university_state = 'DELETED' where id = ?")
@Where(clause = "university_state != 'DELETED'")
@Table(name = "university")
public class UniversityEntity extends AbstractEntity {

    @Column(nullable = false, name = "university_name")
    private String name;

    @Column(unique = true, nullable = false, name = "university_code")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "university_state")
    private UniversityState universityState;

    @OneToMany(mappedBy = "university")
    private List<CourseEntity> courses;

    @OneToMany(mappedBy = "university")
    private List<UniversityYearEntity> universityYearEntities;

}
