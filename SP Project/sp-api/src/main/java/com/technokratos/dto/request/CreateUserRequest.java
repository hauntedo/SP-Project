package com.technokratos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.technokratos.consts.ApiConst.EMAIL_REGEX;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "user request", description = "registration form")
public class CreateUserRequest {

    @JsonProperty("first_name")
    @NotBlank(message = "Should not be empty")
    @Schema(name = "first name", description = "Should not be empty")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Should not be empty")
    @Schema(name = "last name", description = "Should not be empty")
    private String lastName;

    @JsonProperty("email")
    @Schema(name = "email", description = "Should not be empty")
    @Email(regexp = EMAIL_REGEX, message = "Invalid email")
    private String email;
}
