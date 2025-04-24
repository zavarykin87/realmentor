package com.zavarykin.realmentor;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class RealMentorApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RealMentorApplication.class, args);

	}

}
