package com.technokratos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "university_year")
public class UniversityYearEntity extends AbstractEntity {

    @Column(name = "university_year", nullable = false)
    private Integer universityYear;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private UniversityEntity university;

    @OneToMany(mappedBy = "universityYear")
    private List<AcademyGroupEntity> academyGroups;

}
