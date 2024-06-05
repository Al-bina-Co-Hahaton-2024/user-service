package ru.albina.user.dto.response;

import lombok.Data;
import ru.albina.user.domain.Role;
import ru.albina.user.dto.request.UserFullName;

import java.util.List;
import java.util.UUID;

@Data
public class User {

    private UUID id;

    private String login;

    private UserFullName fullName;

    private List<Role> roles;
}
