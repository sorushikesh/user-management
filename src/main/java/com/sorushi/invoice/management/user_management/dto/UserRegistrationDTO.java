package com.sorushi.invoice.management.user_management.dto;

import com.sorushi.invoice.management.user_management.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegistrationDTO {

  @NotBlank(message = "Username is mandatory")
  @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
  private String userName;

  @NotBlank(message = "Email is mandatory")
  @Email(message = "Invalid email format")
  private String userEmailId;

  @NotBlank(message = "Password is mandatory")
  @Size(min = 8, message = "Password must be at least 8 characters long")
  @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
      message =
          "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
  private String password;

  @NotNull(message = "Role is mandatory")
  private Role role;
}
