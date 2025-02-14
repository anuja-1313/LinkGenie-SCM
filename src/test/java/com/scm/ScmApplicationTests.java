package com.scm;

import com.scm.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScmApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService service;

	@Test
	void sendEmailTest(){
		service.sendEmail("artemishunt21@gmail.com",
				"testing SCM email service",
				"Hi, Anuja! just testing the email notif service :)");
	}
}
