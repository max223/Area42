package com.rogiss.project.area42;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:OAuth/OAuthConfig.xml")

public class Area42Application {

	public static void main(String[] args) {
    		SpringApplication.run(Area42Application.class, args);
	}
}
