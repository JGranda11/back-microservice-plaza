package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.adapter;

import com.pragma.challenge.msvc_plaza.domain.spi.UserPersistencePort;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.client.UserFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFeignAdapter implements UserPersistencePort {
    private final UserFeign userFeign;

    @Override
    public boolean isOwner(Long id) {
        return userFeign.isOwner(id).isOwner();
    }
}
