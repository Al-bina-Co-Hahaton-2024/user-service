package ru.albina.user.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.backlib.configuration.auto.OpenApiConfiguration;
import ru.albina.user.dto.request.UserUpdateDto;
import ru.albina.user.service.UserUpdateService;

import java.util.UUID;

@RestController
@RequestMapping(WebConstants.FULL_WEB + "/users")
@RequiredArgsConstructor
public class UserUpdateController {

    private final UserUpdateService userUpdateService;

    @Operation(
            summary = "Обновить пользователя",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200"
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping("{id}")
    public void createUser(
            @PathVariable("id") UUID id,
            @RequestBody UserUpdateDto userUpdateDto
    ) {
        this.userUpdateService.update(id, userUpdateDto);
    }
}
