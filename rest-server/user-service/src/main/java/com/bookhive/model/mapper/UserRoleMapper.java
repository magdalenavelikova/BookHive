package com.bookhive.model.mapper;


import com.bookhive.model.dto.UserRoleDto;
import com.bookhive.model.entities.UserRoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDto userRoleEntityToUserRoLeDto(UserRoleEntity userRoleEntity);
}
