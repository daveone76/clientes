package com.apiclientesv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class ApiClientesV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiClientesV1Application.class, args);

		System.out.println("HELLO WORLD");
	}



}
