package com.sorushi.invoice.management.user_management.service.serviceImpl;

import com.sorushi.invoice.management.user_management.entity.UsersEntity;
import com.sorushi.invoice.management.user_management.repository.UserRepository;
import com.sorushi.invoice.management.user_management.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UsersEntity user =
        userRepository
            .findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return User.builder()
        .username(user.getUserName())
        .password(user.getPassword())
        .roles(user.getRole().name())
        .build();
  }
}
