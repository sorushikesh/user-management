package com.sorushi.invoice.management.user_management.controller;

import static com.sorushi.invoice.management.user_management.constants.APIEndpoints.API_V1_USER;
import static com.sorushi.invoice.management.user_management.constants.APIEndpoints.REGISTER;

import com.sorushi.invoice.management.user_management.dto.UserRegistrationDTO;
import com.sorushi.invoice.management.user_management.entity.UsersEntity;
import com.sorushi.invoice.management.user_management.service.UserService;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(API_V1_USER)
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(REGISTER)
  public ResponseEntity<String> registerUser(
      @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
    log.info("Registering user with email: {}", userRegistrationDTO.getUserEmailId());
    UsersEntity usersEntity = userService.registerUser(userRegistrationDTO);
    if (Objects.nonNull(usersEntity)) {
      return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("User registration failed due to internal error");
    }
  }
}
