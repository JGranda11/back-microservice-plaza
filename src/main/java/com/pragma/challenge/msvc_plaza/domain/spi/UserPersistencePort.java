package com.pragma.challenge.msvc_plaza.domain.spi;

import com.pragma.challenge.msvc_plaza.domain.model.User;

public interface UserPersistencePort {
    boolean isOwner(Long id);
    User getUser(String id);
}
