package com.widetech.interview.widetech_interview;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderServiceTest.class)
class WidetechInterviewApplicationTests {

	@Test
	void contextLoads() {
	}

}
