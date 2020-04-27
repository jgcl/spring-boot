package com.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Tag(name="application", description = "Health Check API")
@RestController
@RequestMapping(value = "/")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Operation(summary = "Simple blank result to health check", description = "")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success")
	})
	@GetMapping(produces = { "application/json" })
	public String index() {
		return "[]";
	}
}
