package ru.albina.user.dto.request;

import lombok.Data;

@Data
public class UserFullName {

    private String first;

    private String last;

    private String middle;
}
