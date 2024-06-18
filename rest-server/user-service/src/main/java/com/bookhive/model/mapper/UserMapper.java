package com.bookhive.model.mapper;


import com.bookhive.model.dto.UserVO;
import com.bookhive.model.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userVOToUserEntity(UserVO registerDto);
    UserVO userEntityToUserVO(UserEntity userEntity);
//    UserEntity userDtoToUserEntity(UserDto userDto);
//

//    @Mapping(source = "created", target = "created", dateFormat = "dd.MM.yyyy")
//    UserDto userEntityToUserDto(UserEntity userEntity);

}
