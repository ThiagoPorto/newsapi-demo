package com.handpicked.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.handpicked.demo.domain.News;
import com.handpicked.demo.domain.User;
import com.handpicked.demo.domain.UserNews;
import com.handpicked.demo.repository.UserNewsRepository;

@Service
public class UserNewsService {
	
	@Autowired
	private UserNewsRepository userNewsRepository;
	
	@Autowired
	private UserService userService;
	
	public List<UserNews> getByUserId(Long userId) {
		return userNewsRepository.findByUserId(userId);
	}
	
	public void save(UserNews userNews) {
		userNewsRepository.save(userNews);
	}
	
	public UserNews getOneByUserIdAndNewsId(Long userId, Long newsId) {
		return userNewsRepository.findOneByUserIdAndNewsId(userId, newsId);
	}
	
	public void delete(UserNews userNews) {
		userNewsRepository.delete(userNews);
	}
	
	public List<News> getNewsByUserId(int page) {
		User user = getUser();
		return userNewsRepository.findNewsByUserId(user.getId(), PageRequest.of(page - 1, 10));
	}
	
	public Long getCountByUserId() {
		User user = getUser();
		return userNewsRepository.countNewsByUserId(user.getId());
	}
	
	private User getUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getByEmail(userDetails.getUsername());
		return user;
	}
}
