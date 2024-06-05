package ru.albina.user.dto.request;

import lombok.Data;
import ru.albina.user.domain.Role;

import java.util.Set;

@Data
public class UserCreateDto {

    private String login;

    private Set<Role> roles;

    private UserFullName fullName;

    private String password;
}
