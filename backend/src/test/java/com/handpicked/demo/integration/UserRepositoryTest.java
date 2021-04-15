package com.handpicked.demo.integration;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.handpicked.demo.domain.User;
import com.handpicked.demo.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testFindByEmail() {
		String email = "test@demo.com";
		User user1 = new User(email, "123123");
		userRepository.save(user1);
		
		User userFound = userRepository.findOneByEmail(email);
		assertThat(userFound.getEmail()).isEqualTo(email);
	}
}
