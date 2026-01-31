package com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor;

import com.pragma.challenge.msvc_plaza.domain.exception.*;
import com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor.response.ExceptionResponse;
import com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import com.pragma.challenge.msvc_plaza.infrastructure.util.ExceptionResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityAlreadyExists(EntityAlreadyExistsException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(EntityNotFoundException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ExceptionResponse> handleNotAuthorized(NotAuthorizedException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RestaurantDoesNotBelongToUserException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantDoesNotBelongToUser(RestaurantDoesNotBelongToUserException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserRoleMustBeOwnerException.class)
    public ResponseEntity<ExceptionResponse> handleUnderAgedUser(UserRoleMustBeOwnerException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderIsAlreadyAssignedException.class)
    public ResponseEntity<ExceptionResponse> handleOrderIsAlreadyAssigned(OrderIsAlreadyAssignedException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerAlreadyHasProcessingOrderException.class)
    public ResponseEntity<ExceptionResponse> handleCustomerAlreadyHasAProcessingOrder(CustomerAlreadyHasProcessingOrderException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DishDoesNotBelongToOrderRestaurantException.class)
    public ResponseEntity<ExceptionResponse> handleDishDoesNotBelongToOrderRestaurant(DishDoesNotBelongToOrderRestaurantException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotificationWasNotSentException.class)
    public ResponseEntity<ExceptionResponse> handleNotificationWasNotSent(NotificationWasNotSentException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderIsNotInPreparationStateException.class)
    public ResponseEntity<ExceptionResponse> handleOrderNotDone(OrderIsNotInPreparationStateException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SecurityPinNoMatchException.class)
    public ResponseEntity<ExceptionResponse> handleSecurityPinNotMatch(SecurityPinNoMatchException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderNotDoneException.class)
    public ResponseEntity<ExceptionResponse> handleOrderIsNotDonde(OrderNotDoneException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<ValidationExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationExceptionResponse exceptionResponse = ValidationExceptionResponse.builder()
                .statusCode(e.getStatusCode().value())
                .status(HttpStatus.resolve(e.getStatusCode().value()))
                .timestamp(LocalDateTime.now())
                .errors(e.getFieldErrors().stream().map(field -> {
                    StringBuilder sb = new StringBuilder();
                    String rejectedValue = field.getRejectedValue() == null ? "null" : field.getRejectedValue().toString();
                    sb.append(field.getDefaultMessage()).append(": ").append(rejectedValue);
                    return sb.toString();
                }).toList())
                .message(e.getBody().getDetail()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}
