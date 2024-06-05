package ru.albina.user.mapper;

import org.mapstruct.Mapper;
import ru.albina.user.configuration.MapperConfiguration;
import ru.albina.user.domain.Role;

@Mapper(config = MapperConfiguration.class)
public interface RoleMapper {
    String ROLE_PREFIX = "ROLE_";

    default String to(Role roleEntity) {
        return ROLE_PREFIX + roleEntity.toString();
    }
}
