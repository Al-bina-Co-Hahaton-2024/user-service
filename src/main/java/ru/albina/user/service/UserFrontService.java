package ru.albina.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.user.dto.response.User;
import ru.albina.user.mapper.UserMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFrontService {

    private final UserMapper userMapper;

    private final UserService userService;

    @Transactional(readOnly = true)
    public User getById(UUID id) {
        return this.userMapper.to(
                this.userService.getUserEntity(id)
        );
    }
}
