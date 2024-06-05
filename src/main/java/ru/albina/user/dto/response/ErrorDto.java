package ru.albina.user.dto.response;

import lombok.Builder;

@Builder
public record ErrorDto(String error) {}
