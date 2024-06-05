package ru.albina.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.albina.user.domain.UserEntity;
import ru.albina.user.domain.UserEntity_;
import ru.albina.user.dto.request.UserFinder;
import ru.albina.user.user.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFindService {

    private final UserRepository userRepository;

    public List<UUID> find(UserFinder finder) {
        Specification<UserEntity> specification = Specification.where(null);

        if (Objects.nonNull(finder.getFullName())) {
            specification = specification.or((root, query, criteriaBuilder) -> criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(UserEntity_.fullName)),
                    "%" + finder.getFullName().toLowerCase() + "%"
            ));
        }
        return this.userRepository.findAll(specification)
                .stream().map(UserEntity::getId)
                .toList();
    }
}
