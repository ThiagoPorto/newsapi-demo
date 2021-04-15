package com.handpicked.demo.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.handpicked.demo.domain.News;
import com.handpicked.demo.payload.NewsAPIResponse;
import com.handpicked.demo.payload.NewsRequest;
import com.handpicked.demo.service.NewsService;

@RestController
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@GetMapping("/news")
	public NewsAPIResponse getNews(@RequestParam(value = "country", defaultValue = "br") String country,
			@RequestParam(value = "category", defaultValue = "") String category,
			@RequestParam(value = "query", defaultValue = "") String query,
			@RequestParam(value = "page", defaultValue = "1") String page) throws JsonMappingException, JsonProcessingException {
		
		return this.newsService.getNews(country, category, query, page);
	}
	
	@PostMapping("/news")
	public void saveNews(@RequestBody NewsRequest newsRequest) {
		News news = new News(newsRequest.getAuthor(), newsRequest.getTitle(), newsRequest.getDescription(), 
				newsRequest.getUrl(), newsRequest.getUrlToImage(), newsRequest.getPublishedAt(), newsRequest.getContent());
		newsService.saveNews(news);
	}
	
	@DeleteMapping("/news/{newsId}")
	public void removeSavedNews(@PathVariable("newsId") Long newsId) {
		newsService.removeSavedNews(newsId);
	}
}
