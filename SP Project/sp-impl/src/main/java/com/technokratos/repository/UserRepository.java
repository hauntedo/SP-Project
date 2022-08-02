package com.technokratos.repository;

import com.technokratos.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findOneByEmail(String email);

    boolean existsUserEntityById(UUID userId);


}
