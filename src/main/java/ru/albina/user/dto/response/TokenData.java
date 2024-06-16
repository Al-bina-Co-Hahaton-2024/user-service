package ru.albina.user.dto.response;

import lombok.Builder;
import ru.albina.user.domain.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
public record TokenData(
        String token,
        Set<Role> roles,
        LocalDateTime expired
) {
}
