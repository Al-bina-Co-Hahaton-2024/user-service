package ru.albina.user.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.albina.backlib.model.jwt.UserJwt;
import ru.albina.backlib.service.jwt.UserJwtService;
import ru.albina.user.domain.UserEntity;
import ru.albina.user.mapper.RoleMapper;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JwtGenerateService {

    private final RoleMapper roleMapper;


    private final UserJwtService userJwtService;


    public UserJwt generateTokenData(UserEntity userEntity) {
        return UserJwt.builder()
                .id(userEntity.getId())
                .expired(LocalDateTime.now().plus(Duration.ofDays(30)))//TODO change!
                .username(userEntity.getLogin())
                .roles(userEntity.getRoles().stream().map(this.roleMapper::to).toList())
                .build();
    }


    public String generateToken(UserJwt data) {
        return this.userJwtService.generate(data);
    }
}
