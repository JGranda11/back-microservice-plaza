package com.pragma.challenge.msvc_plaza.domain.model.order;

import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;

import java.util.List;

public class Order {
    private Long id;
    private String customerId;
    private Restaurant restaurant;
    private List<OrderDish> dishes;
    private OrderState state;
    private Employee assignedEmployee;
    private String securityPin;

    private Order(OrderBuilder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.dishes = builder.dishes;
        this.restaurant = builder.restaurant;
        this.state = builder.state;
        this.assignedEmployee = builder.assignedEmployee;
        this.securityPin = builder.securityPin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<OrderDish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public String getSecurityPin() {
        return securityPin;
    }

    public void setSecurityPin(String securityPin) {
        this.securityPin = securityPin;
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private Long id;
        private String customerId;
        private List<OrderDish> dishes;
        private Restaurant restaurant;
        private OrderState state;
        private Employee assignedEmployee;
        private String securityPin;

        public OrderBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public OrderBuilder dishes(List<OrderDish> dishes) {
            this.dishes = dishes;
            return this;
        }

        public OrderBuilder restaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public OrderBuilder state(OrderState state) {
            this.state = state;
            return this;
        }

        public OrderBuilder assignedEmployee(Employee assignedEmployee) {
            this.assignedEmployee = assignedEmployee;
            return this;
        }

        public OrderBuilder securityPin(String securityPin) {
            this.securityPin = securityPin;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
