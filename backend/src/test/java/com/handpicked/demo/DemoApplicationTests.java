package com.handpicked.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = DemoApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class DemoApplicationTests {
	
	@Test
	void contextLoads() throws Exception {
	}
}
