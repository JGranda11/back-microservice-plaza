package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.util;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
public class PaginationJpa {
    private Integer page;
    private String column;
    private boolean ascending;
    private Integer pageSize;

    public Pageable createPageable() {
        Pageable pageable;
        if (this.getColumn() == null || this.getColumn().isEmpty()) {
            pageable = PageRequest.of(this.getPage(), this.pageSize);
        } else {
            Sort.Direction direction = this.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sort = Sort.by(direction, this.getColumn());
            pageable = PageRequest.of(this.getPage(), this.pageSize).withSort(sort);
        }
        return pageable;
    }
}
