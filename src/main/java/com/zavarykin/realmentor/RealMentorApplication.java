package com.zavarykin.realmentor;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;

@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class RealMentorApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RealMentorApplication.class, args);

	}

}
