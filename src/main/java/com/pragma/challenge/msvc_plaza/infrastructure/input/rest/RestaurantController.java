package com.pragma.challenge.msvc_plaza.infrastructure.input.rest;

import com.pragma.challenge.msvc_plaza.application.dto.request.EmployeeRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.RestaurantRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.pagination.PaginationRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.pagination.RestaurantPageQuery;
import com.pragma.challenge.msvc_plaza.application.dto.response.EmployeeResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.PageResponse;
import com.pragma.challenge.msvc_plaza.application.dto.response.RestaurantResponse;
import com.pragma.challenge.msvc_plaza.application.handler.RestaurantHandler;
import com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor.response.ExceptionResponse;
import com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantHandler restaurantHandler;

    @Operation(summary = "Create a new restaurant")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "201",
                    description = "Restaurant created successfully",
                    content = @Content(schema = @Schema(implementation = RestaurantResponse.class))
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "User does not exist",
                    content = @Content
            ),

            @ApiResponse(
                    responseCode = "409",
                    description = "User is not an owner",
                    content = @Content
            ),

            @ApiResponse(
                    responseCode = "409",
                    description = "Restaurant with given NIT already exists",
                    content = @Content
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Validation errors in request data",
                    content = @Content
            )
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RestaurantResponse> createRestaurant(
            @RequestBody @Valid RestaurantRequest restaurantRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restaurantHandler.createRestaurant(restaurantRequest));
    }


    @Operation(summary = "Register relation between Employee and Restaurant")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Relation between employee and restaurant has been registered successfully",
                    content =  @Content(schema = @Schema(implementation = RestaurantResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Given user has no Owner role",
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode ="409",
                    description = "A Restaurant with that NIT already exists",
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validations don't pass",
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PreAuthorize("hasAnyRole('OWNER')")
    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> registerEmployee(@RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                restaurantHandler.registerEmployee(employeeRequest)
        );
    }

    @Operation(summary = "Search all restaurants, and retrieve as page")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "restaurant has been found",
                    content =  @Content(schema = @Schema(implementation = RestaurantResponse.class))
            ),
    })
    @GetMapping
    public ResponseEntity<PageResponse<RestaurantResponse>> findRestaurants(@Nullable RestaurantPageQuery query){
        PaginationRequest pagination = PaginationRequest.build(query);
        return ResponseEntity.ok(
                restaurantHandler.findPage(pagination)
        );
    }
}
