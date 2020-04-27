package com.application;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "/")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success", content = { @Content(mediaType = "application/json") })
	})
	@GetMapping
	public String index() {
		return "[]";
	}
}
