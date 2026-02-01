package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.client;

import com.pragma.challenge.msvc_plaza.infrastructure.configuration.feign.FeignClientConfiguration;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response.IsOwnerResponse;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response.ShortUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "USER-CLIENT",
        url = "http://localhost:8081/v1/users",
        configuration = FeignClientConfiguration.class)
public interface UserFeign {
    @GetMapping("/{id}/is-owner")
    IsOwnerResponse isOwner(@PathVariable Long id);

    @GetMapping("/{id}/short")
    ShortUserResponse getUserShortResponse(@PathVariable String id);
}
