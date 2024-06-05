package ru.albina.user.service.finder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.albina.user.domain.UserEntity;
import ru.albina.user.dto.request.UserFinder;
import ru.albina.user.dto.response.SimpleUser;
import ru.albina.user.mapper.UserMapper;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFrontFinderService {
    private final UserFindService userFindService;

    private final UserMapper userMapper;


    @Transactional(readOnly = true)
    public List<UUID> find(UserFinder finder) {
        return this.userFindService.find(finder).stream().map(UserEntity::getId).toList();
    }

    @Transactional(readOnly = true)
    public List<SimpleUser> find(Set<UUID> ids) {
        return this.userFindService.findByIds(ids)
                .stream().map(userMapper::toSimple)
                .toList();
    }
}
