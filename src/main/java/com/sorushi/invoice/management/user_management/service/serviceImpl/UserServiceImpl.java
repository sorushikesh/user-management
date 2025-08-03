package com.sorushi.invoice.management.user_management.service.serviceImpl;

import com.sorushi.invoice.management.user_management.constants.ErrorCodes;
import com.sorushi.invoice.management.user_management.dto.UserRegistrationDTO;
import com.sorushi.invoice.management.user_management.entity.UsersEntity;
import com.sorushi.invoice.management.user_management.exception.UserManagementException;
import com.sorushi.invoice.management.user_management.repository.UserRepository;
import com.sorushi.invoice.management.user_management.service.UserService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final MessageSource messageSource;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public UserServiceImpl(
      MessageSource messageSource, PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.messageSource = messageSource;
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  public UsersEntity registerUser(UserRegistrationDTO dto) {

    if (Objects.isNull(dto)) {
      log.error("User registration failed as userRegistrationDTO is null");
    }

    if (userRepository.existsByUserName(dto.getUserName())) {
      throw new UserManagementException(
          HttpStatus.BAD_REQUEST,
          ErrorCodes.USERNAME_ALREADY_EXIST,
          new Object[] {dto.getUserName()},
          messageSource,
          null);
    }

    if (userRepository.existsByUserEmailId(dto.getUserEmailId())) {
      throw new UserManagementException(
          HttpStatus.BAD_REQUEST,
          ErrorCodes.EMAIL_ID_ALREADY_EXIST,
          new Object[] {dto.getUserName()},
          messageSource,
          null);
    }

    UsersEntity user = new UsersEntity();
    user.setUserName(dto.getUserName());
    user.setUserEmailId(dto.getUserEmailId());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    user.setRole(dto.getRole());
    return userRepository.save(user);
  }
}
