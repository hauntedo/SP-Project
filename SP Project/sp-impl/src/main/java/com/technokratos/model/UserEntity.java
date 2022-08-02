package com.technokratos.model;


import com.technokratos.utils.enums.Role;
import com.technokratos.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "account")
public class UserEntity extends AbstractEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @Column(name = "hash_password")
    private String hashPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_course",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private Set<CourseEntity> courses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "academy_group_id")
    private AcademyGroupEntity academyGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state", nullable = false)
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private Role role;


}
