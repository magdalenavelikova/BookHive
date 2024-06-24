package com.bookhive.model.mapper;

import com.bookhive.model.dto.UserRegisterDto;
import com.bookhive.model.dto.UserVO;
import com.bookhive.model.entities.UserEntity;
import com.bookhive.model.entities.UserRoleEntity;
import com.bookhive.model.enums.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",

    date = "2024-06-21T09:34:59+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"

)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity userRegisterDtoToUserEntity(UserRegisterDto userRegisterDto) {
        if ( userRegisterDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( userRegisterDto.getUsername() );
        userEntity.setEmail( userRegisterDto.getEmail() );
        userEntity.setPassword( userRegisterDto.getPassword() );
        userEntity.setFirstName( userRegisterDto.getFirstName() );
        userEntity.setLastName( userRegisterDto.getLastName() );
        userEntity.setAvatar( userRegisterDto.getAvatar() );

        return userEntity;
    }

    @Override
    public UserVO userEntityToUserVO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        Role role = userEntityRoleRole( userEntity );
        if ( role != null ) {
            userVO.setRole( role.name() );
        }
        userVO.setId( userEntity.getId() );
        userVO.setUsername( userEntity.getUsername() );

        return userVO;
    }

    private Role userEntityRoleRole(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }
        UserRoleEntity role = userEntity.getRole();
        if ( role == null ) {
            return null;
        }
        Role role1 = role.getRole();
        if ( role1 == null ) {
            return null;
        }
        return role1;
    }
}
