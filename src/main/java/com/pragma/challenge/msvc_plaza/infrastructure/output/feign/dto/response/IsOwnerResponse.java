package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IsOwnerResponse {
    boolean isOwner;
}
