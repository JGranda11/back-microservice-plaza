package com.pragma.challenge.msvc_plaza.domain.util.filter;

public class EmployeeFilter {
    private String employeeId;
    private String restaurantId;

    private EmployeeFilter(EmployeeFilterBuilder builder) {
        this.employeeId = builder.employeeId;
        this.restaurantId = builder.restaurantId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public static EmployeeFilterBuilder builder() {
        return new EmployeeFilterBuilder();
    }

    public static class EmployeeFilterBuilder {
        private String employeeId;
        private String restaurantId;

        public EmployeeFilterBuilder employeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public EmployeeFilterBuilder restaurantId(String restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public EmployeeFilter build() {
            return new EmployeeFilter(this);
        }
    }
}
