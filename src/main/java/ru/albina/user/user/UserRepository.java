package ru.albina.user.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.albina.user.domain.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByLogin(String login);
}
