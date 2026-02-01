package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.mapper.request;

import com.pragma.challenge.msvc_plaza.domain.model.messaging.Notification;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.request.NotificationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationRequestMapper {
    NotificationRequest toRequest(Notification response);
    List<NotificationRequest> toDomains(List<Notification> responses);
}
