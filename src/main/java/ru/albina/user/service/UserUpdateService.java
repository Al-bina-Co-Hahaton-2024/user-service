package ru.albina.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.user.dto.request.UserUpdateDto;
import ru.albina.user.mapper.UserMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserService userService;

    private final UserMapper userMapper;

    @Transactional
   public void update(UUID userId, UserUpdateDto userUpdateDto){
       final var user =  this.userService.getUserEntity(userId);

       this.userMapper.update(user, userUpdateDto);
    }
}
