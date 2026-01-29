package com.pragma.challenge.msvc_plaza.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageResponse<T> {
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Integer count;
    private Long totalCount;
    private List<T> content;

}
