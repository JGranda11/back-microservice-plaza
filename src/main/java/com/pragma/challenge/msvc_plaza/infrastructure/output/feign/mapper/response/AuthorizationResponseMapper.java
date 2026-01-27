package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.mapper.response;

import com.pragma.challenge.msvc_plaza.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response.AuthorizationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorizationResponseMapper {
    AuthorizedUser toResponse(AuthorizationResponse response);
    List<AuthorizedUser> toResponses(List<AuthorizationResponse> responseList);
}
