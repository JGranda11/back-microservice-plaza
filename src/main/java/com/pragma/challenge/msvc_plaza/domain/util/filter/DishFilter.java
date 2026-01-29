package com.pragma.challenge.msvc_plaza.domain.util.filter;

import com.pragma.challenge.msvc_plaza.domain.util.enums.DishState;

public class DishFilter {
    private Long restaurantId;
    private String category;
    private DishState state;

    public DishFilter(DishFilterBuilder builder) {
        this.restaurantId = builder.restaurantId;
        this.category = builder.category;
        this.state = builder.state;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DishState getState() {
        return state;
    }

    public void setState(DishState state) {
        this.state = state;
    }

    public static DishFilterBuilder builder(){
        return new DishFilterBuilder();
    }

    public static class DishFilterBuilder{
        private Long restaurantId;
        private String category;
        private DishState state;

        public DishFilterBuilder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public DishFilterBuilder category(String category) {
            this.category = category;
            return this;
        }

        public DishFilterBuilder state(DishState state) {
            this.state = state;
            return this;
        }

        public DishFilter build(){
            return new DishFilter(this);
        }
    }
}
