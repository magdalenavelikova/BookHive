package com.bookhive.service;

import com.bookhive.exception.UserLoginException;
import com.bookhive.exception.UserNotFoundException;
import com.bookhive.exception.UserNotUniqueException;
import com.bookhive.model.dto.*;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;

    public UserVO registerNewUserAccount(MultipartFile file, UserRegisterDto userRegisterDto, Boolean isOauth) throws IOException {
        UserEntity userEntity = userMapper.userRegisterDtoToUserEntity(userRegisterDto);
        userEntity.setCreated(LocalDateTime.now());
        if (userRoleRepository.findByRole(Role.USER).isPresent()) {
            userEntity.setRole(userRoleRepository.findByRole(Role.USER).get());
        }

        if (isOauth) {
            userEntity.setAvatar(userRegisterDto.getAvatar());
            userEntity.setEnabled(true);
        } else {
            userEntity.setAvatar(getPictureUrl(file));
            userEntity.setEnabled(false);
            String rowPassword = userEntity.getPassword();
            String password = passwordEncoder.encode(rowPassword);
            userEntity.setPassword(password);
        }

        return userMapper.userEntityToUserVO(userRepository.save(userEntity));

    }


    public UserVO getUserCredentials(AuthRequest authRequest) {
        Optional<UserEntity> user = this.userRepository.findByUsername(authRequest.getUsername());
        if (user.isEmpty()) {
            throw new UserLoginException("Incorrect Username or Password");
        }
        boolean isPasswordsMatch = passwordEncoder.matches(authRequest.getPassword(),
                user.get().getPassword());
        if (!isPasswordsMatch) {
            throw new UserLoginException("Incorrect Username or Password");
        }
        if (!user.get().isEnabled()) {
            throw new UserLoginException("User is not activated");
        }
        return userMapper.userEntityToUserVO(user.get());

    }

    public UserVO getAuthCredentials(UserRegisterDto request) throws IOException {
        Optional<UserEntity> user = this.userRepository.findByEmail(request.getEmail());
        boolean isOauth = true;
        UserVO userVO;
        if (user.isPresent()) {
            userVO = userMapper.userEntityToUserVO(user.get());
        } else {
            userVO = registerNewUserAccount(null, request, isOauth);
        }
        return userVO;
    }

    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::userEntityToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto editUserCredential(UserEditDto userEditDto, Long id) {
        UserEntity edit = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        Optional<UserEntity> userEmail = userRepository.findByEmail(userEditDto.getEmail());
        Optional<UserEntity> username = userRepository.findByUsername(userEditDto.getUsername());

        if (username.isPresent() && !Objects.equals(edit.getId(), username.get().getId())) {
            throw new UserNotUniqueException(userEditDto.getUsername());
        }

        if (userEmail.isPresent() && !Objects.equals(edit.getId(), userEmail.get().getId())) {
            throw new UserNotUniqueException(userEditDto.getEmail());
        } else {
            UserEntity temp = userMapper.userEditDtoToUserEntity(userEditDto);

            if (!temp.equals(edit)) {
                edit.setUsername(temp.getUsername());
                edit.setEmail(temp.getEmail());
                edit.setFirstName(temp.getFirstName());
                edit.setLastName(temp.getLastName());
                edit.setModified(LocalDateTime.now());
                edit.setRole(userRoleRepository.findByRole(Role.valueOf(temp.getRole().getRole().toString())).get());
                edit.setModified(LocalDateTime.now());
                return userMapper.userEntityToUserDto(userRepository.save(edit));
            }
            return userMapper.userEntityToUserDto(edit);
        }
    }
//
//    @Override
//    public UserDto getUserByVerificationToken(VerificationToken verificationToken) {
//        return userMapper.userEntityToUserDto(verificationToken.getUser());
//    }
//
//    @Override
//    public UserDto getUserByUserEmail(AuthRequest request) {
//        return userMapper.userEntityToUserDto(userRepository.findByEmail(request.getUsername())
//                .orElseThrow(() -> new UserNotFoundException(request.getUsername())));
//    }
//

    public UserEntity getUserByUserEmail(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public UserEditDto getUser(Long id) {
        return userMapper
                .userEntityToUserEditDto(userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id)));
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            //  tokenRepository.deleteByUserId(id);
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public boolean changePassword(UserChangePasswordDto userChangePasswordDto, String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        boolean isAuthenticated = passwordEncoder.matches(userChangePasswordDto.getOldPassword(), userEntity.getPassword());
        if (isAuthenticated) {
            userEntity.setPassword(passwordEncoder.encode(userChangePasswordDto.getNewPassword()));
            userEntity.setModified(LocalDateTime.now());
            userRepository.save(userEntity);
            return true;
        } else {
            return false;
        }
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
            pictureUrl = cloudinaryService.uploadAvatar(file);
        }
        return pictureUrl;
    }


}
