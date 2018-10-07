package com.inShowAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableSwagger2
@EnableAsync
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = { "com.inShowAdmin","org.n3r" })
@MapperScan(basePackages = { "com.inShowAdmin.mapper" })
public class InShowAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(InShowAdminApplication.class, args);
	}
}
