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
import ru.albina.user.dto.response.SimpleUser;
import ru.albina.user.service.finder.UserFrontFinderService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping({WebConstants.FULL_WEB + "/users", WebConstants.FULL_PRIVATE + "/users"})
@RequiredArgsConstructor
public class UserFinderController {

    private final UserFrontFinderService userFrontFinderService;


    @Operation(
            summary = "Поиск пользователя по ID",
            security = @SecurityRequirement(name = OpenApiConfiguration.JWT),
            responses = {
                    @ApiResponse(
                            description = "ОК",
                            responseCode = "200"
                    )
            }
    )
    //TODO @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("find-by-ids")
    public List<SimpleUser> find(
            @RequestBody Set<UUID> ids
    ) {
        return this.userFrontFinderService.find(ids);
    }

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
        return this.userFrontFinderService.find(userFinder);
    }
}
