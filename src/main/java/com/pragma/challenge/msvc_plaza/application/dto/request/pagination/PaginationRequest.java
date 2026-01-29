package com.pragma.challenge.msvc_plaza.application.dto.request.pagination;

import com.pragma.challenge.msvc_plaza.domain.util.DomainConstants;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaginationRequest {
    private Integer page;
    private String column;
    private boolean ascending;
    private Integer pageSize;

    public PaginationRequest(@Nullable PageQuery query){
        setDefaultState();
        if(query == null) return;
        if(query.getSortBy() != null) this.column = query.getSortBy();
        if (query.getPage() != null) this.page = query.getPage();
        if (query.getAsc() != null) this.ascending = query.getAsc();
        if (query.getPageSize() != null) this.pageSize = query.getPageSize();
    }

    public PaginationRequest(@Nullable RestaurantPageQuery query) {
        setDefaultState();
        if(query == null) return;
        if (query.getPage() != null) this.page = query.getPage();
        if (query.getAsc() != null) this.ascending = query.getAsc();
        if (query.getPageSize() != null) this.pageSize = query.getPageSize();
    }

    public static PaginationRequest build(@Nullable PageQuery query){
        return new PaginationRequest(query);
    }

    public static PaginationRequest build(@Nullable RestaurantPageQuery query){
        return new PaginationRequest(query);
    }

    private void setDefaultState(){
        this.column = null;
        this.page = 0;
        this.ascending = true;
        this.pageSize = DomainConstants.PAGE_SIZE;
    }
}
