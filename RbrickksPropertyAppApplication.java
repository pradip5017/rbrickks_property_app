package com.rbrickks;

import org.springframework.boot.SpringApplication;   
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.rbrickks.model") 
@EnableJpaRepositories("com.rbrickks.repository")
public class RbrickksPropertyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbrickksPropertyAppApplication.class, args);
	}

}
