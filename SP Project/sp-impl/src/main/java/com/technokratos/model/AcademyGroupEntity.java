package com.technokratos.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "academy_group")
public class AcademyGroupEntity extends AbstractEntity {

    @Column(nullable = false, name = "academy_group_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "university_year_id")
    private UniversityYearEntity universityYear;

    @OneToMany(mappedBy = "academyGroup")
    private List<UserEntity> users;

}
