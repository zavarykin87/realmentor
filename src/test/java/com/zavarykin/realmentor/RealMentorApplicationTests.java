package com.zavarykin.realmentor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.sql.SQLException;

@SpringBootTest
class RealMentorApplicationTests {

    @Test
	void contextLoads() throws InterruptedException, SQLException {
        String[] args = new String[1];
        args[0] = "";
        RealMentorApplication.main(args);
	}

}
