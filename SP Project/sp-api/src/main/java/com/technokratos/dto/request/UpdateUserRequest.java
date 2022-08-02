package com.technokratos.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.technokratos.validation.ValidDate;
import com.technokratos.validation.NonWhiteSpace;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.technokratos.consts.ApiConst.PHONE_REGEX;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "form for update user")
public class UpdateUserRequest {

    @JsonProperty("first_name")
    @NotBlank(message = "Should not be empty")
    @Schema(name = "first name", description = "Should not be empty")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Should not be empty")
    @Schema(name = "last name", description = "Should not be empty")
    private String lastName;

    @JsonProperty("birthdate")
    @ValidDate
    @NotBlank(message = "Should not be empty")
    @Schema(name = "birth date", example = "1991-01-01")
    private String birthDate;

    @JsonProperty("phone")
    @Pattern(regexp = PHONE_REGEX,
    message = "Incorrect number")
    @Schema(name = "phone", example = "80000000000, +79912112312")
    private String phone;


    @JsonProperty("address")
    @NotBlank(message = "Should not be empty")
    @Schema(name = "address", example = "Kazan")
    private String address;

    @JsonProperty("telegram")
    @NonWhiteSpace
    @NotBlank(message = "Should not be empty")
    @Schema(name = "telegram", example = "hauntedo")
    private String telegram;



}
