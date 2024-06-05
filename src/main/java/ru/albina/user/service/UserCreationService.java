package ru.albina.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.user.dto.response.User;
import ru.albina.user.dto.request.UserCreateDto;
import ru.albina.user.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserCreationService {

    private final UserService userService;
    private final UserRolesService userRolesService;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User create(UserCreateDto userCreateDto) {
        final var entity = this.userService.save(
                this.userMapper.from(userCreateDto)
                        .setPassword(this.passwordEncoder.encode(userCreateDto.getPassword()))
        );

        return this.userMapper.to(this.userRolesService.addRoles(entity.getId(), userCreateDto.getRoles()));
    }
}
