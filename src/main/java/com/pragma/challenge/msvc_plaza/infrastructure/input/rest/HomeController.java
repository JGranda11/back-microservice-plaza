package com.pragma.challenge.msvc_plaza.infrastructure.input.rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/home")
public class HomeController {
    @Operation(summary = "And endpoint to test if app is running")
    @GetMapping
    public String home(){
        return "Plaza's Microservice";
    }
}
