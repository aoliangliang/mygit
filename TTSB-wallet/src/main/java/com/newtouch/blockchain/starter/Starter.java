package com.newtouch.blockchain.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 */
@ImportResource(locations = { "classpath*:/beans/**/*.xml" })
@ComponentScan(basePackages = { "cn.kklazy", "com.newtouch" })
@SpringBootApplication
//@EnableScheduling
public class Starter extends SpringBootServletInitializer{
	
	
	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
	
}
