package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.mapper.response;

import com.pragma.challenge.msvc_plaza.domain.model.User;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response.ShortUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShortUserResponseMapper {

    User toDomain(ShortUserResponse response);
    List<User> toDomains(List<ShortUserResponse> responses);
}
