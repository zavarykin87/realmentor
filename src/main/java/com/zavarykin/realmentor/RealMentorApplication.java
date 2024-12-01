package com.zavarykin.realmentor;


import com.zavarykin.realmentor.dto.UserTO;
import com.zavarykin.realmentor.entity.AuthorityEntity;
import com.zavarykin.realmentor.entity.Role;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.AuthorityRepository;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class RealMentorApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RealMentorApplication.class, args);

	}

}
