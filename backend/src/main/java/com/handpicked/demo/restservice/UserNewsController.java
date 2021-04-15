package com.handpicked.demo.restservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.handpicked.demo.domain.News;
import com.handpicked.demo.service.UserNewsService;

@RestController
public class UserNewsController {

	@Autowired
	private UserNewsService userNewsService;
	
	@GetMapping("/user-news/{page}")
	public ResponseEntity<List<News>> getUserNews(@PathVariable("page") int page) {
		List<News> result = this.userNewsService.getNewsByUserId(page);
		Long countByUserId = this.userNewsService.getCountByUserId();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("x-total-count", String.valueOf(countByUserId));
		
		return new ResponseEntity<>(result,
	            httpHeaders,
	            HttpStatus.OK);
	}
}
