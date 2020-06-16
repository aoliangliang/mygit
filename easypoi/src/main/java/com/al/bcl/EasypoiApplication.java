package com.al.bcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.al.bcl")
public class EasypoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasypoiApplication.class, args);
	}

}
