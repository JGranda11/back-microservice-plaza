package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.User;
import com.pragma.challenge.msvc_plaza.domain.spi.UserPersistencePort;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.client.UserFeign;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.mapper.response.ShortUserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFeignAdapter implements UserPersistencePort {
    private final UserFeign userFeign;
    private final ShortUserResponseMapper shortUserResponseMapper;

    @Override
    public boolean isOwner(Long id) {
        return userFeign.isOwner(id).isOwner();
    }

    @Override
    public User getUser(String id) {
        return shortUserResponseMapper.toDomain(
                userFeign.getUserShortResponse(id)
        );
    }
}
