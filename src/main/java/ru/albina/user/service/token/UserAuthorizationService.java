package ru.albina.user.service.token;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.user.domain.UserEntity;
import ru.albina.user.dto.response.TokenData;
import ru.albina.user.exception.UserAuthorizationException;
import ru.albina.user.service.UserService;

@Service
@RequiredArgsConstructor
public class UserAuthorizationService {

    private final PasswordEncoder passwordEncoder;

    private final JwtGenerateService jwtGenerateService;

    private final UserService userService;


    @Transactional(readOnly = true)
    public TokenData authorization(String username, String password) {
        final var user = this.userService.getByLogin(username);

        final var result = this.passwordEncoder.matches(password, user.getPassword());
        if (!result) {
            throw new UserAuthorizationException(username);
        }

        return this.generate(user);
    }

    @Transactional(readOnly = true)
    public TokenData refreshToken(String username) {
        return this.generate(
                this.userService.getByLogin(username)
        );
    }

    private TokenData generate(UserEntity user) {
        final var jwtData = this.jwtGenerateService.generateTokenData(user);

        return TokenData.builder()
                .token(this.jwtGenerateService.generateToken(jwtData))
                .roles(user.getRoles())
                .expired(jwtData.expired())
                .build();
    }
}
