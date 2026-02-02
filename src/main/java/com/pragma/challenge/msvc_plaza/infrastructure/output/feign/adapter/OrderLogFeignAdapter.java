package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.adapter;

import com.pragma.challenge.msvc_plaza.domain.model.order.Order;
import com.pragma.challenge.msvc_plaza.domain.spi.report.OrderReportPort;
import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.client.OrderReportFeign;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.request.NewOrderLogRequest;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.mapper.request.OrderLogRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderLogFeignAdapter implements OrderReportPort {

    private final OrderReportFeign orderReportFeign;
    private final OrderLogRequestMapper orderLogRequestMapper;
    @Override
    public void sendNewOrderReport(Order order) {
        NewOrderLogRequest newLog = orderLogRequestMapper.toRequest(order);
        orderReportFeign.saveNewOrderLog(newLog);
    }

    @Override
    public void addEmployeeToOrderLog(Long orderId, String employeeId) {
        orderReportFeign.addAssignedEmployee(orderId, employeeId);
    }

    @Override
    public void addNewStateToOrderLog(Long orderId, OrderState state) {
        orderReportFeign.addNewState(orderId, state);
    }
}
