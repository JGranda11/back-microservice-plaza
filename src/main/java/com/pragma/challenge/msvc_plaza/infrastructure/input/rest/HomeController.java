package com.pragma.challenge.msvc_plaza.infrastructure.input.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/home")
public class HomeController {
    @GetMapping
    public String home(){
        return "Plaza's Microservice";
    }
}
