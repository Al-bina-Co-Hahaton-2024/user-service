package ru.albina.user.service.finder;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.user.domain.UserEntity;
import ru.albina.user.domain.UserEntity_;
import ru.albina.user.dto.request.UserFinder;
import ru.albina.user.user.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFindService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserEntity> find(UserFinder finder) {
        Specification<UserEntity> specification = Specification.where(null);

        if (Objects.nonNull(finder.getFullName())) {
            specification = specification.or((root, query, criteriaBuilder) -> criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(UserEntity_.fullName)),
                    "%" + finder.getFullName().toLowerCase() + "%"
            ));
        }
        return this.userRepository.findAll(specification);
    }


    @Transactional(readOnly = true)
    public List<UserEntity> findByIds(Collection<UUID> ids) {
        return this.userRepository.findAllById(ids);
    }
}
