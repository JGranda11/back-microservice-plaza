package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.mapper;

import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;
import com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.util.PaginationJpa;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationJpaMapper {
    PaginationJpa toJpa(PaginationData paginationData);
}
