package com.pragma.challenge.msvc_plaza.domain.util.filter;

import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;

import java.util.List;

public class OrderFilter {
    private String customerId;
    private String assignedEmployeeId;
    private String restaurantId;
    private List<OrderState> states;

    private OrderFilter(OrderFilterBuilder builder) {
        this.customerId = builder.customerId;
        this.assignedEmployeeId = builder.assignedEmployeeId;
        this.restaurantId = builder.restaurantId;
        this.states = builder.states;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(String assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderState> getStates() {
        return states;
    }

    public void setStates(List<OrderState> states) {
        this.states = states;
    }

    public static OrderFilterBuilder builder() {
        return new OrderFilterBuilder();
    }

    public static class OrderFilterBuilder {
        private String customerId;
        private String assignedEmployeeId;
        private String restaurantId;
        private List<OrderState> states;

        public OrderFilterBuilder customerId(String clientId) {
            this.customerId = clientId;
            return this;
        }

        public OrderFilterBuilder assignedEmployeeId(String assignedEmployeeId) {
            this.assignedEmployeeId = assignedEmployeeId;
            return this;
        }

        public OrderFilterBuilder restaurantId(String restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public OrderFilterBuilder states(List<OrderState> states) {
            this.states = states;
            return this;
        }

        public OrderFilter build() {
            return new OrderFilter(this);
        }
    }
}
