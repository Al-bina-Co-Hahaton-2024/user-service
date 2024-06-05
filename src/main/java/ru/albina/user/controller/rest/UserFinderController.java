package ru.albina.user.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.albina.backlib.configuration.WebConstants;
import ru.albina.backlib.configuration.auto.OpenApiConfiguration;
import ru.albina.user.dto.request.UserFinder;
import ru.albina.user.service.UserFindService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({WebConstants.FULL_WEB + "/users", WebConstants.FULL_PRIVATE + "/users"})
@RequiredArgsConstructor
public class UserFinderController {

    private final UserFindService userFindService;

    @Operation(
            summary = "Поиск пользователя",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200"
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("find-ids")
    public List<UUID> find(
            @RequestBody UserFinder userFinder
    ) {
        return this.userFindService.find(userFinder);
    }
}
