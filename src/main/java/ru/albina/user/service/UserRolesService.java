package ru.albina.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.user.client.MedicalClient;
import ru.albina.user.domain.Role;
import ru.albina.user.domain.UserEntity;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRolesService {

    private final UserService userService;

    private final MedicalClient medicalClient;

    @Transactional
    public UserEntity addRoles(UUID userId, Collection<Role> roles) {
        final var entity = this.userService.getUserEntity(userId);
        entity.getRoles().addAll(roles);
        if (roles.contains(Role.DOCTOR)) {
            this.medicalClient.createDoctor(userId);
        }
        return entity;
    }

    @Transactional
    public UserEntity addRole(UUID userId, Role role) {
        return this.addRoles(userId, Set.of(role));
    }

    @Transactional
    public UserEntity deleteRole(UUID userId, Role role) {
        return this.deleteRoles(userId, Set.of(role));
    }


    @Transactional
    public UserEntity deleteRoles(UUID userId, Collection<Role> roles) {
        final var entity = this.userService.getUserEntity(userId);
        if (roles.contains(Role.DOCTOR)) {
            this.medicalClient.deleteDoctor(userId);
        }
        entity.getRoles().removeAll(roles);
        return entity;
    }
}
