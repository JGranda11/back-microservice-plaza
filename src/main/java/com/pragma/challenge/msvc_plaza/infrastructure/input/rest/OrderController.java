package com.pragma.challenge.msvc_plaza.infrastructure.input.rest;

import com.pragma.challenge.msvc_plaza.application.dto.request.filter.OrderFilterRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.order.OrderRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.pagination.PageQuery;
import com.pragma.challenge.msvc_plaza.application.dto.request.pagination.PaginationRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.PageResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderCreatedResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.order.OrderResponse;
import com.pragma.challenge.msvc_plaza.application.handler.OrderHandler;
import com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor.response.ExceptionResponse;
import com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderHandler orderHandler;

    @Operation(summary = "Create dish order")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Dish order created successfully",
                    content = @Content(schema = @Schema(implementation = OrderCreatedResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dish not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Dish does not belong to the restaurant",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already has a processing order",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation errors",
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<OrderCreatedResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                orderHandler.createOrder(orderRequest)
        );
    }


    @Operation(summary = "Get dishes from the restaurant where work the actual user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Orders from the restaurant where retrieved, if there were",
                    content =  @Content(schema = @Schema(implementation = OrderCreatedResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Who made the petition, doesn't work in the restaurant",
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
    })
    @GetMapping
    public ResponseEntity<PageResponse<OrderResponse>> getOrders(OrderFilterRequest filter, PageQuery query){
        PaginationRequest paginationRequest = PaginationRequest.build(query);
        return ResponseEntity.ok(
                orderHandler.findOrders(filter, paginationRequest)
        );
    }

}
