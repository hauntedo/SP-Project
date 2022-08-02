package com.technokratos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cv")
@SuperBuilder
@Setter
@Getter
public class CvEntity extends AbstractEntity {

    @Column(name = "experience_description")
    private String experienceDescription;

    @Column(name = "portfolio_links")
    private String portfolioLinks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cv_skill",
            joinColumns = @JoinColumn(name = "cv_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
    )
    private Set<SkillEntity> skills = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "file_id")
    private FileEntity file;

    @OneToOne
    @JoinColumn(name = "account_id")
    private UserEntity user;



}
