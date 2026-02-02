package com.pragma.challenge.msvc_plaza.infrastructure.output.feign.client;

import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;
import com.pragma.challenge.msvc_plaza.infrastructure.configuration.feign.FeignClientConfiguration;
import com.pragma.challenge.msvc_plaza.infrastructure.output.feign.dto.request.NewOrderLogRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "ORDER-REPORT-CLIENT",
        url = "http://localhost:8084/v1/order-logs" ,
        configuration = FeignClientConfiguration.class
)
public interface OrderReportFeign {
    @PostMapping
    public void saveNewOrderLog(@RequestBody NewOrderLogRequest request);

    @PatchMapping("/orders/{orderId}/state")
    public void addNewState(@PathVariable Long orderId, @RequestParam OrderState add);

    @PatchMapping("/orders/{orderId}/assigned-employee")
    public void addAssignedEmployee(@PathVariable Long orderId, @RequestParam String add);
}
