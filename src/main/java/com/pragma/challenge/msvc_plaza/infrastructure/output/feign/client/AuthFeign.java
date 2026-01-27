package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.client;


import com.pragma.challenge.msvc_plaza.infrastructure.configuration.feign.FeignClientConfiguration;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response.AuthorizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "AUTH-CLIENT",
        url ="http://localhost:8081/v1/auth",
        configuration = FeignClientConfiguration.class
)
public interface AuthFeign {

    @GetMapping("/authorize")
    AuthorizationResponse authorize(@RequestParam String token);
}
