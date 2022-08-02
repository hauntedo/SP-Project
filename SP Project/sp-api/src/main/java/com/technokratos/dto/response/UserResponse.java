package com.technokratos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "user", description = "info about user")
public class UserResponse {

    @JsonProperty("id")
    @Schema(name = "user id")
    private UUID id;

    @JsonProperty("first_name")
    @Schema(name = "first name", example = "Rustem")
    private String firstName;

    @JsonProperty("last_name")
    @Schema(name = "last name", example = "Karimov")
    private String lastName;

    @JsonProperty("birthdate")
    @Schema(name = "birthdate", example = "1991-01-01")
    private LocalDate birthDate;

    @JsonProperty("phone")
    @Schema(name = "phone", example = "800000000")
    private String phone;

    @JsonProperty("address")
    @Schema(name = "address", example = "Kazan")
    private String address;

    @JsonProperty("telegram")
    @Schema(name = "telegram", example = "hauntedo")
    private String telegram;

    @JsonProperty("email")
    @Schema(name = "email", example = "rustem.karimov.2002@gmail.com")
    private String email;

    @JsonProperty("academy_group")
    @Schema(name = "academy group", description = "info about academy group")
    private AcademyGroupResponse academyGroup;

    @JsonProperty("cv")
    @Schema(name = "cv", description = "info about skills, experience, portfolio links and cv file")
    private CvResponse cvResponse;

    @JsonProperty("courses")
    @Schema(name = "courses", description = "info about courses that user attend")
    private Set<CourseResponse> courses = new HashSet<>();

    @JsonProperty("role")
    @Schema(name = "user role", example = "ADMIN")
    private String role;

    @JsonProperty("state")
    @Schema(name = "user state", example = "BANNED")
    private String state;

}
