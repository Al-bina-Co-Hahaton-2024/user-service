package ru.albina.user.dto.response;

import lombok.Data;
import ru.albina.user.dto.request.UserFullName;

import java.util.UUID;

@Data
public class SimpleUser {

    private UUID id;


    private UserFullName fullName;
}
