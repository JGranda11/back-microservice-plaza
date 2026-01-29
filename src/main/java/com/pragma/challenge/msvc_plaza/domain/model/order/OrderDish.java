package com.pragma.challenge.msvc_plaza.domain.model.order;

import com.pragma.challenge.msvc_plaza.domain.model.Dish;

public class OrderDish {
    private Long id;
    private Dish dish;
    private Integer quantity;

    private OrderDish(OrderDishBuilder builder) {
        this.id = builder.id;
        this.dish = builder.dish;
        this.quantity = builder.quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static OrderDishBuilder builder() {
        return new OrderDishBuilder();
    }

    public static class OrderDishBuilder {
        private Long id;
        private Dish dish;
        private Integer quantity;

        public OrderDishBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderDishBuilder dish(Dish dish) {
            this.dish = dish;
            return this;
        }

        public OrderDishBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderDish build() {
            return new OrderDish(this);
        }
    }
}
