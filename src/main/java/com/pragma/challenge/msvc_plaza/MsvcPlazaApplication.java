package com.pragma.challenge.msvc_plaza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcPlazaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcPlazaApplication.class, args);
	}

}
