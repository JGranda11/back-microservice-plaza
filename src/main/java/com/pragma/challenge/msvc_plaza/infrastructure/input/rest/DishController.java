package com.pragma.challenge.msvc_plaza.infrastructure.input.rest;

import com.pragma.challenge.msvc_plaza.application.dto.request.DishRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.PatchDishRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.DishResponse;
import com.pragma.challenge.msvc_plaza.application.handler.DishHandler;
import com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor.response.ExceptionResponse;
import com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishHandler dishHandler;

    @Operation(summary = "Creates a new dish for a restaurant")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "The dish has been created",
                    content = @Content(schema = @Schema(implementation = DishResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "A restaurant with that Id doesn't exists",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validations don't pass",
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<DishResponse> createDish(@RequestBody @Valid DishRequest dishRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                dishHandler.createDish(dishRequest)
        );
    }

    @Operation(summary = "Modify price and description of a dish")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Modify price and description of a dish",
                    content =  @Content(schema = @Schema(implementation = DishResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Desired dish hasn't been found",
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validations don't pass",
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PostMapping("/{id}")
    public ResponseEntity<DishResponse> patchDish(@PathVariable Long id,
                                                  @RequestBody @Valid PatchDishRequest patchDishRequest){
        return ResponseEntity.ok(dishHandler.modifyDish(id, patchDishRequest));
    }
}
