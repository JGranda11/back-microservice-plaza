package com.pragma.challenge.msvc_plaza.domain.spi.report;

import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;

public interface OrderReportPort {
    void sendNewOrderReport(Order order);
    void addEmployeeToOrderLog(Long orderId, String employeeId);
    void addNewStateToOrderLog(Long orderId, OrderState state);
}
