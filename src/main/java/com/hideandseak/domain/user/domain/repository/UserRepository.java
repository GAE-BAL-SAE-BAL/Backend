package com.hideandseak.domain.user.domain.repository;

import com.hideandseak.domain.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserAccount(String userAccount);


}
