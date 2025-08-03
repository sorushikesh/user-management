package com.sorushi.invoice.management.user_management.service;

import com.sorushi.invoice.management.user_management.dto.UserRegistrationDTO;
import com.sorushi.invoice.management.user_management.entity.UsersEntity;

public interface UserService {

  UsersEntity registerUser(UserRegistrationDTO userRegistrationDTO);
}
