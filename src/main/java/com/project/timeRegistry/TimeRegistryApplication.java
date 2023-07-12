package com.project.timeRegistry;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Time register API", version = "1.0.0", description = "Api to manage worked time"),
		servers = @Server(url = "http://localhost:8080")
)
public class TimeRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeRegistryApplication.class, args);
	}

}
