package com.pragma.challenge.msvc_plaza.application.mapper.request;

import com.pragma.challenge.msvc_plaza.application.dto.request.pagination.PaginationRequest;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationRequestMapper {
    PaginationData toDomain(PaginationRequest paginationRequest);
}
