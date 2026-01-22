package com.pragma.challenge.msvc_plaza.infrastructure.configuration.feign;

import feign.Feign;
import feign.Logger;
import feign.QueryMapEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    QueryMapEncoder feignQueryMapEncoder(FeignQueryBuilder feignQueryBuilder) {
        Feign.builder().queryMapEncoder(feignQueryBuilder);
        return feignQueryBuilder;
    }
}
