package com.technokratos.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "form for update user academy group info")
public class UserAcademyGroupRequest {

    @JsonProperty("academy_group_id")
    @Schema(description = "academy group id")
    private UUID academyGroupId;

}
