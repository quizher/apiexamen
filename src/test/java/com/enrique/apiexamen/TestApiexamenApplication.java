package com.enrique.apiexamen;

import org.springframework.boot.SpringApplication;

public class TestApiexamenApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiexamenApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
