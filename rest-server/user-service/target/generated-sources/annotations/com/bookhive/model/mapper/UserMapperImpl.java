package com.bookhive.model.mapper;

import com.bookhive.model.dto.UserVO;
import com.bookhive.model.entities.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T16:46:27+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity userVOToUserEntity(UserVO registerDto) {
        if ( registerDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        if ( registerDto.getId() != null ) {
            userEntity.setId( Long.parseLong( registerDto.getId() ) );
        }
        userEntity.setEmail( registerDto.getEmail() );
        userEntity.setPassword( registerDto.getPassword() );
        userEntity.setFirstName( registerDto.getFirstName() );
        userEntity.setLastName( registerDto.getLastName() );
        userEntity.setCountry( registerDto.getCountry() );
        userEntity.setCity( registerDto.getCity() );

        return userEntity;
    }

    @Override
    public UserVO userEntityToUserVO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        if ( userEntity.getId() != null ) {
            userVO.setId( String.valueOf( userEntity.getId() ) );
        }
        userVO.setEmail( userEntity.getEmail() );
        userVO.setPassword( userEntity.getPassword() );
        userVO.setFirstName( userEntity.getFirstName() );
        userVO.setLastName( userEntity.getLastName() );
        userVO.setCountry( userEntity.getCountry() );
        userVO.setCity( userEntity.getCity() );

        return userVO;
    }
}
