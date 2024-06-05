package ru.albina.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.albina.user.configuration.MapperConfiguration;
import ru.albina.user.domain.UserEntity;
import ru.albina.user.dto.response.User;
import ru.albina.user.dto.request.UserCreateDto;

import java.util.UUID;

@Mapper(config = MapperConfiguration.class, imports = UUID.class)
public interface UserMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "firstName", source = "fullName.first")
    @Mapping(target = "lastName", source = "fullName.last")
    @Mapping(target = "middleName", source = "fullName.middle")
    @Mapping(target = "roles", ignore = true)
    UserEntity from(UserCreateDto dto);

    @Mapping(target = "fullName.first", source = "firstName")
    @Mapping(target = "fullName.last", source = "lastName")
    @Mapping(target = "fullName.middle", source = "middleName")
    User to(UserEntity userEntity);
}
