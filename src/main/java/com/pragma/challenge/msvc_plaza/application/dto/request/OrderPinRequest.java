package com.pragma.challenge.msvc_plaza.application.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderPinRequest {
    String securityPin;
}
