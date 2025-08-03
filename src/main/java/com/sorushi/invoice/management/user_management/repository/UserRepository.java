package com.sorushi.invoice.management.user_management.repository;

import com.sorushi.invoice.management.user_management.entity.UsersEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

  Optional<UsersEntity> findByUserName(String userName);

  Optional<UsersEntity> findByUserEmailId(String userEmailId);

  boolean existsByUserName(String userName);

  boolean existsByUserEmailId(String userEmailId);
}
