package com.pragma.challenge.msvc_plaza.domain.model;

public class DishCategory {
    private Long id;
    private String description;

    public DishCategory() {
    }

    public DishCategory(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String description;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public DishCategory build() {
            return new DishCategory(
                    id,
                    description
            );
        }
    }
}
