package com.pragma.challenge.msvc_plaza.domain.model;

public class Employee {
    private Long id;
    private Restaurant restaurant;

    public Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.restaurant = builder.restaurant;
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

    public static EmployeeBuilder builder(){
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder{
        private Long id;
        private Restaurant restaurant;

        public EmployeeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder restaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public Employee build(){
            return new Employee(this);
        }
    }
}
