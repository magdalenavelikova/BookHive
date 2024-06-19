package com.bookhive.model.mapper;


import com.bookhive.model.dto.UserRegisterDto;
import com.bookhive.model.dto.UserVO;
import com.bookhive.model.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity userRegisterDtoToUserEntity(UserRegisterDto userRegisterDto);
    @Mapping(source = "role.role", target = "role")
    UserVO userEntityToUserVO(UserEntity userEntity);
//    UserEntity userDtoToUserEntity(UserDto userDto);
//

//    @Mapping(source = "created", target = "created", dateFormat = "dd.MM.yyyy")
//    UserDto userEntityToUserDto(UserEntity userEntity);

}
