package com.bookhive.service;

import com.bookhive.exception.UserLoginException;
import com.bookhive.model.dto.AuthRequest;
import com.bookhive.model.dto.UserRegisterDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;

    public UserVO registerNewUserAccount(MultipartFile file, UserRegisterDto userRegisterDto) throws IOException {
        UserEntity userEntity = userMapper.userRegisterDtoToUserEntity(userRegisterDto);
        String rowPassword = userEntity.getPassword();
        String password = passwordEncoder.encode(rowPassword);
        userEntity.setPassword(password);
        userEntity.setCreated(LocalDateTime.now());

        if (userRoleRepository.findByRole(Role.USER).isPresent()) {
            userEntity.setRole(userRoleRepository.findByRole(Role.USER).get());
        }
        userEntity.setEnabled(false);
        userEntity.setAvatar(getPictureUrl(file));
        UserVO userVO = userMapper.userEntityToUserVO(userRepository.save(userEntity));
        userVO.setRole(userEntity.getRole().getRole().toString());
        return userVO;
    }

    public void initRole() {

        if (userRoleRepository.count() == 0) {
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
            UserRoleEntity roleAuthor = new UserRoleEntity();
            roleAuthor.setRole(Role.AUTHOR);
            roleAuthor.setCreated(LocalDateTime.now());
            userRoleRepository.save(roleAuthor);
            UserRoleEntity roleExpert = new UserRoleEntity();
            roleExpert.setRole(Role.EXPERT);
            roleExpert.setCreated(LocalDateTime.now());
            userRoleRepository.save(roleExpert);
        }


    }

    private String getPictureUrl(MultipartFile file) throws IOException {
        String pictureUrl = "";

        if (file != null) {
            pictureUrl = cloudinaryService.uploadImage(file);
        }
        return pictureUrl;

    }

    public UserVO getUserCredentials(AuthRequest userLoginDTO) {
        Optional<UserEntity> user = this.userRepository.findByUsername(userLoginDTO.getUsername());
        if (user.isEmpty()) {
            throw new UserLoginException("Incorrect Username ot Password");
        }
        boolean isPasswordsMatch = passwordEncoder.matches(userLoginDTO.getPassword(),
                user.get().getPassword());
        if (!isPasswordsMatch) {
            throw new UserLoginException("Incorrect Username ot Password");
        }
        UserVO userVO = new UserVO();
        userVO.setId(user.get().getId());
        userVO.setUsername(user.get().getUsername());
        Optional<UserRoleEntity> role = this.userRoleRepository.findById(user.get().getRole().getId());
        userVO.setRole(String.valueOf(role.get().getRole()));
        return userVO;
    }

}
