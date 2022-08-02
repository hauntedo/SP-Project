package com.technokratos.utils.mapper;

import com.technokratos.dto.request.CreateUserRequest;
import com.technokratos.dto.request.UpdateUserRequest;
import com.technokratos.dto.response.UserResponse;
import com.technokratos.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(UserEntity user);

    UserEntity toUserEntity(CreateUserRequest userRequest);

    void toUserEntity(UpdateUserRequest userRequest, @MappingTarget UserEntity user);

}
