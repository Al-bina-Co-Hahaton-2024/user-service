package ru.albina.user.dto.request;

import lombok.Data;
import ru.albina.user.domain.Role;

@Data
public class UserRoleModification {

    private Role role;
}
