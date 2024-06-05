package ru.albina.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.user.domain.UserEntity;
import ru.albina.user.exception.EntityNotFoundException;
import ru.albina.user.user.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserEntity save(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }


    public UserEntity getUserEntity(UUID id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );
    }

    public UserEntity getByLogin(String login) {
        return this.userRepository.findByLogin(login).orElseThrow(
                () -> new EntityNotFoundException("User with login " + login + " not found")
        );
    }
}
