package ru.albina.user.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TokenData(
        String token,
        LocalDateTime expired
) {
}
