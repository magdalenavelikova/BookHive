package com.bookhive.model.mapper;

import com.bookhive.model.dto.UserDto;
import com.bookhive.model.dto.UserEditDto;
import com.bookhive.model.dto.UserRegisterDto;
import com.bookhive.model.dto.UserVO;
import com.bookhive.model.entities.UserEntity;
import com.bookhive.model.entities.UserRoleEntity;
import com.bookhive.model.enums.Role;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-26T15:39:04+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_11900521056 = DateTimeFormatter.ofPattern( "dd.MM.yyyy" );

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

    @Override
    public UserEditDto userEntityToUserEditDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserEditDto userEditDto = new UserEditDto();

        Role role = userEntityRoleRole( userEntity );
        if ( role != null ) {
            userEditDto.setRole( role.name() );
        }
        userEditDto.setUsername( userEntity.getUsername() );
        userEditDto.setEmail( userEntity.getEmail() );
        userEditDto.setFirstName( userEntity.getFirstName() );
        userEditDto.setLastName( userEntity.getLastName() );
        userEditDto.setAvatar( userEntity.getAvatar() );

        return userEditDto;
    }

    @Override
    public UserEntity userEditDtoToUserEntity(UserEditDto editUserDto) {
        if ( editUserDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setRole( userEditDtoToUserRoleEntity( editUserDto ) );
        userEntity.setUsername( editUserDto.getUsername() );
        userEntity.setEmail( editUserDto.getEmail() );
        userEntity.setFirstName( editUserDto.getFirstName() );
        userEntity.setLastName( editUserDto.getLastName() );
        userEntity.setAvatar( editUserDto.getAvatar() );

        return userEntity;
    }

    @Override
    public UserEntity userDtoToUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setRole( userDtoToUserRoleEntity( userDto ) );
        userEntity.setId( userDto.getId() );
        if ( userDto.getCreated() != null ) {
            userEntity.setCreated( LocalDateTime.parse( userDto.getCreated() ) );
        }
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setEmail( userDto.getEmail() );
        userEntity.setFirstName( userDto.getFirstName() );
        userEntity.setLastName( userDto.getLastName() );
        userEntity.setAvatar( userDto.getAvatar() );

        return userEntity;
    }

    @Override
    public UserDto userEntityToUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( userEntity.getCreated() != null ) {
            userDto.setCreated( dateTimeFormatter_dd_MM_yyyy_11900521056.format( userEntity.getCreated() ) );
        }
        Role role = userEntityRoleRole( userEntity );
        if ( role != null ) {
            userDto.setRole( role.name() );
        }
        userDto.setId( userEntity.getId() );
        userDto.setUsername( userEntity.getUsername() );
        userDto.setEmail( userEntity.getEmail() );
        userDto.setFirstName( userEntity.getFirstName() );
        userDto.setLastName( userEntity.getLastName() );
        userDto.setAvatar( userEntity.getAvatar() );

        return userDto;
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

    protected UserRoleEntity userEditDtoToUserRoleEntity(UserEditDto userEditDto) {
        if ( userEditDto == null ) {
            return null;
        }

        UserRoleEntity userRoleEntity = new UserRoleEntity();

        if ( userEditDto.getRole() != null ) {
            userRoleEntity.setRole( Enum.valueOf( Role.class, userEditDto.getRole() ) );
        }

        return userRoleEntity;
    }

    protected UserRoleEntity userDtoToUserRoleEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserRoleEntity userRoleEntity = new UserRoleEntity();

        if ( userDto.getRole() != null ) {
            userRoleEntity.setRole( Enum.valueOf( Role.class, userDto.getRole() ) );
        }

        return userRoleEntity;
    }
}
