package com.handpicked.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.handpicked.demo.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findOneByEmail(String email);
}
