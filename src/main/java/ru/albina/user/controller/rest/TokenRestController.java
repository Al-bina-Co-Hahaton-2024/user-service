package ru.albina.user.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.backlib.configuration.auto.OpenApiConfiguration;
import ru.albina.backlib.model.security.LibPrincipal;
import ru.albina.user.dto.request.UserAuthorization;
import ru.albina.user.dto.response.TokenData;
import ru.albina.user.service.token.UserAuthorizationService;

@Slf4j
@RestController
@RequestMapping(WebConstants.FULL_WEB + "/tokens")
@RequiredArgsConstructor
public class TokenRestController {

    private final UserAuthorizationService userAuthorizationService;

    @Operation(
            summary = "Получение токена пользователя",
            responses = {
                    @ApiResponse(
                            description = "JWT-токен пользователя",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TokenData.class))
                    )
            }
    )
    @PostMapping
    public TokenData auth(
            @Valid @RequestBody UserAuthorization userAuthorization
    ) {
        return this.userAuthorizationService.authorization(
                userAuthorization.getLogin(),
                userAuthorization.getPassword()
        );
    }

    @Operation(
            summary = "Обновление токена пользователя",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "JWT-токен пользователя",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TokenData.class))
                    )
            }
    )
    @PatchMapping
    public TokenData refresh(LibPrincipal principal) {
        return this.userAuthorizationService.refreshToken(principal.getName());
    }
}

