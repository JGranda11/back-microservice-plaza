package com.pragma.challenge.msvc_plaza.application.dto.request.pagination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantPageQuery {
    private Integer page;
    private Boolean asc;
    private Integer pageSize;
}
