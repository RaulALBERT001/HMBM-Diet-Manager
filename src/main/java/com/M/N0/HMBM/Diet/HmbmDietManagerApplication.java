package com.M.N0.HMBM.Diet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HmbmDietManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmbmDietManagerApplication.class, args);
	}

}
