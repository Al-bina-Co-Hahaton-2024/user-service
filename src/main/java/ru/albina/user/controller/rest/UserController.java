package ru.albina.user.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.backlib.configuration.auto.OpenApiConfiguration;
import ru.albina.user.dto.request.UserCreateDto;
import ru.albina.user.dto.request.UserRoleModification;
import ru.albina.user.dto.response.User;
import ru.albina.user.service.UserCreationService;
import ru.albina.user.service.UserRolesService;

import java.util.UUID;

@RestController
@RequestMapping({WebConstants.FULL_WEB + "/users", WebConstants.FULL_PRIVATE + "/users"})
@RequiredArgsConstructor
public class UserController {

    private final UserCreationService userCreationService;

    private final UserRolesService userRolesService;

    @Operation(
            summary = "Создать пользователя",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = User.class))
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public User createUser(
            @RequestBody UserCreateDto userCreateDto
    ) {
        return this.userCreationService.create(userCreateDto);
    }


    @Operation(
            summary = "Добавить роль",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = User.class))
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping("{id}/roles")
    public void addRole(
            @PathVariable("id") UUID id,
            @RequestBody UserRoleModification userRoleModification
    ) {
        this.userRolesService.addRole(id, userRoleModification.getRole());
    }


    @Operation(
            summary = "Удалить роль",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = User.class))
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("{id}/roles")
    public void deleteRole(
            @PathVariable("id") UUID id,
            @RequestBody UserRoleModification userRoleModification
    ) {
        this.userRolesService.deleteRole(id, userRoleModification.getRole());
    }
}
