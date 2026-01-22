package com.pragma.challenge.msvc_plaza.infrastructure.configuration.advisor.response;

import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Integer statusCode;
    private HttpStatusCode status;
    private String message;
    private LocalDateTime timestamp;
}
