package com.zavarykin.realmentor;

import com.zavarykin.realmentor.domain.entity.UserEntity;
import com.zavarykin.realmentor.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;

@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class RealMentorApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RealMentorApplication.class, args);

		String message = context.getMessage("message", new Object[]{"Андрей"}, Locale.forLanguageTag("en-GB"));
		System.out.println(message);

		UserService service = context.getBean(UserService.class);

	}

}
