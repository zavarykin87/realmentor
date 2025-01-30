package com.zavarykin.realmentor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.lang.reflect.Method;
import java.sql.SQLException;

@SpringBootTest
class RealMentorApplicationTests {

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Test
	void contextLoads() {

	}

}
