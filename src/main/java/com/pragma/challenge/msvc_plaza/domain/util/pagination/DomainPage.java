package com.pragma.challenge.msvc_plaza.domain.util.pagination;

import java.util.List;

public class DomainPage <T>{
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Integer count;
    private Long totalCount;
    private List<T> content;

    private DomainPage(DomainPageBuilder<T> builder) {
        this.page = builder.page;
        this.pageSize = builder.pageSize;
        this.totalPages = builder.totalPages;
        this.count = builder.count;
        this.totalCount = builder.totalCount;
        this.content = builder.content;
    }

    public DomainPage(Integer page,
                      Integer pageSize,
                      Integer totalPages,
                      Integer count,
                      Long totalCount,
                      List<T> content) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.count = count;
        this.totalCount = totalCount;
        this.content = content;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public static <T> DomainPageBuilder<T> builder() {
        return new DomainPageBuilder<>();
    }

    public static class DomainPageBuilder<T>{
        Integer page;
        Integer pageSize;
        Integer totalPages;
        Integer count;
        Long totalCount;
        List<T> content;

        public DomainPageBuilder<T> page(Integer page) {
            this.page = page;
            return this;
        }

        public DomainPageBuilder<T> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public DomainPageBuilder<T> totalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public DomainPageBuilder<T> count(Integer count) {
            this.count = count;
            return this;
        }

        public DomainPageBuilder<T> totalCount(Long totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public DomainPageBuilder<T> content(List<T> content) {
            this.content = content;
            return this;
        }

        public DomainPage<T> build() {
            return new DomainPage<>(this);
        }
    }
}
