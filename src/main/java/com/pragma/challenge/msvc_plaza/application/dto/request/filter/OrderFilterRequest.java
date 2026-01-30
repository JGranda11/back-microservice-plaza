package com.pragma.challenge.msvc_plaza.application.dto.request.filter;

import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderFilterRequest {
    private List<OrderState> states;
}
