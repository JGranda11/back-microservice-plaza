package com.pragma.challenge.msvc_plaza.domain.util.pagination;

public class PaginationData {
    private Integer page;
    private String column;
    private boolean ascending;
    private Integer pageSize;

    private PaginationData(PaginationDataBuilder builder) {
        this.page = builder.page;
        this.column = builder.column;
        this.ascending = builder.ascending;
        this.pageSize = builder.pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public static PaginationDataBuilder builder(){
        return new PaginationDataBuilder();
    }

    public static class PaginationDataBuilder{
        private Integer page;
        private String column;
        private boolean ascending;
        private Integer pageSize;

        public PaginationDataBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public PaginationDataBuilder column(String column) {
            this.column = column;
            return this;
        }

        public PaginationDataBuilder ascending(boolean ascending) {
            this.ascending = ascending;
            return this;
        }

        public PaginationDataBuilder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PaginationData build(){
            return new PaginationData(this);
        }
    }
}
