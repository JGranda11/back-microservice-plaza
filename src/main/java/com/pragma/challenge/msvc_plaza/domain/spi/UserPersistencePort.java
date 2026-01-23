package com.pragma.challenge.msvc_plaza.domain.spi;

public interface UserPersistencePort {
    boolean isOwner(Long id);
}
