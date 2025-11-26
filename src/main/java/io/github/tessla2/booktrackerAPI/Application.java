package io.github.tessla2.booktrackerAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Enables JPA Auditing for automatic population of auditing fields
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
