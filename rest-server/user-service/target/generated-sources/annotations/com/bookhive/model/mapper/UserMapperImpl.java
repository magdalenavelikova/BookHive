package com.bookhive.model.mapper;

import com.bookhive.model.dto.UserVO;
import com.bookhive.model.entities.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T13:47:52+0300",
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

        return userEntity;
    }

    @Override
    public UserVO userEntityToUserVO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        return userVO;
    }
}
