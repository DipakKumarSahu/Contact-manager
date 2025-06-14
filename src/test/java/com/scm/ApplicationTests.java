package com.scm;

import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.scm.services.EmailService;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}


@Autowired
private EmailService service;

@Test
void testSendEmail() {
    service.sendEmail("dipu958@outlook.com", "Test Subject", "Arrey Bhai Kesa hai tu");
}

}



