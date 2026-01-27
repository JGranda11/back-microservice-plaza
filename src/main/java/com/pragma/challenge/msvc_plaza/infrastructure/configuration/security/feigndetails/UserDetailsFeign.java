package com.pragma.challenge.msvc_plaza.infrastructure.configuration.security.feigndetails;

import com.pragma.challenge.msvc_plaza.domain.api.security.AuthorizationServicePort;
import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_plaza.infrastructure.util.ConfigurationConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsFeign implements UserDetailsService {
    private final AuthorizationServicePort authorizationServicePort;
    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        AuthorizedUser authResponse = authorizationServicePort.authorize(token);

        Collection<? extends GrantedAuthority> authorities =
                Set.of(new SimpleGrantedAuthority(ConfigurationConstants.ROLE_PREFIX + authResponse.getRole().name()));

        return new User(authResponse.getId(), token, authorities);
    }
}
