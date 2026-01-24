package com.pragma.challenge.msvc_plaza.domain.model;

import com.pragma.challenge.msvc_plaza.domain.util.enums.DishState;

public class Dish {
    private Long id;
    private Restaurant restaurant;
    private String name;
    private String description;
    private Long price;
    private DishCategory category;
    private String imageUrl;
    private DishState state;

    public Dish() {
    }

    public Dish(Long id,
                Restaurant restaurant,
                String name,
                String description,
                Long price,
                DishCategory category,
                String imageUrl,
                DishState state) {
        this.id = id;
        this.restaurant = restaurant;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DishState getState() {
        return state;
    }

    public void setState(DishState state) {
        this.state = state;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Restaurant restaurant;
        private String name;
        private String description;
        private Long price;
        private DishCategory category;
        private String imageUrl;
        private DishState state;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder restaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(Long price) {
            this.price = price;
            return this;
        }

        public Builder category(DishCategory category) {
            this.category = category;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder state(DishState state) {
            this.state = state;
            return this;
        }

        public Dish build() {
            return new Dish(
                    id,
                    restaurant,
                    name,
                    description,
                    price,
                    category,
                    imageUrl,
                    state
            );
        }
    }
}
