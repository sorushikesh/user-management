package com.sorushi.invoice.management.user_management.entity;

import com.sorushi.invoice.management.user_management.enums.Role;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "user_table")
@Data
public class UsersEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name", nullable = false, length = 50)
  private String userName;

  @Column(name = "user_email_id", nullable = false, unique = true, length = 100)
  private String userEmailId;

  @Column(name = "password", nullable = false, length = 60)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_roles", nullable = false)
  private Role role;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
