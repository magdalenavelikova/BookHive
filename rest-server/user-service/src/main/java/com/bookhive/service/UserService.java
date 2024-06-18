package com.bookhive.service;

import com.bookhive.model.dto.UserVO;
import com.bookhive.model.entities.UserEntity;
import com.bookhive.model.entities.UserRoleEntity;
import com.bookhive.model.enums.Role;
import com.bookhive.model.mapper.UserMapper;
import com.bookhive.repository.UserRepository;
import com.bookhive.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserVO registerNewUserAccount(UserVO userVo) {
        UserEntity userEntity = userMapper.userVOToUserEntity(userVo);
        String rowPassword = userEntity.getPassword();
        String password = passwordEncoder.encode(rowPassword);
        userEntity.setPassword(password);
        userEntity.setCreated(LocalDateTime.now());
        if (userRoleRepository.findByRole(Role.USER).isPresent()) {
            userEntity.addRole(userRoleRepository.findByRole(Role.USER).get());
        }
        userEntity.setEnabled(true);

        UserVO userVO = userMapper.userEntityToUserVO(userRepository.save(userEntity));
        return userVO;
    }

    public void initRole() {

        if (userRoleRepository.count() == 0 ) {
            UserRoleEntity roleAdmin = new UserRoleEntity();
            roleAdmin.setRole(Role.ADMIN);
            roleAdmin.setCreated(LocalDateTime.now());
            userRoleRepository.save(roleAdmin);
            UserRoleEntity roleModerator = new UserRoleEntity();
            roleModerator.setRole(Role.MODERATOR);
            roleModerator.setCreated(LocalDateTime.now());
            userRoleRepository.save(roleModerator);
            UserRoleEntity roleUser = new UserRoleEntity();
            roleUser.setRole(Role.USER);
            roleUser.setCreated(LocalDateTime.now());
            userRoleRepository.save(roleUser);


        }


    }
}
