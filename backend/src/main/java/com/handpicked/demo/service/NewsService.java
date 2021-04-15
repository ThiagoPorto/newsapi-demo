package com.handpicked.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handpicked.demo.domain.News;
import com.handpicked.demo.domain.User;
import com.handpicked.demo.domain.UserNews;
import com.handpicked.demo.payload.NewsAPIResponse;
import com.handpicked.demo.repository.NewsRepository;

@Service
public class NewsService {

	private static final String BASE_URL = "http://newsapi.org/v2/top-headlines?";
	private static final String API_KEY_PARAM = "apiKey=c954691ffe8d4957aa00f14f7d3f7773";

	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserNewsService userNewsService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public NewsAPIResponse getNews(String country, String category, String query, String page) throws JsonMappingException, JsonProcessingException {
		String url = BASE_URL;
		
		if (!country.isEmpty()) {
			url += "country=" + country + "&";
		}
		
		if (!category.isEmpty()) {
			url += "category=" + category + "&";
		}
		
		if (!query.isEmpty()) {
			url += "q=" + query + "&";
		}
		
		if (!page.isEmpty()) {
			url += "page=" + page + "&";
		}
		
		url += "pageSize=10&";
		
		url += API_KEY_PARAM;
		
		String jsonString = new RestTemplate().getForObject(url, String.class);
		NewsAPIResponse newsAPIResponse = objectMapper.readValue(jsonString, NewsAPIResponse.class);
		markAsSavedNews(newsAPIResponse.getArticles());
		return newsAPIResponse;
	}
	
	private void markAsSavedNews(List<News> newsFromApi) {
		User user = getUser();
		List<UserNews> newsFromUser = userNewsService.getByUserId(user.getId());
		
		for (UserNews userNews : newsFromUser) {
			for (News news : newsFromApi) {
				if (news.equals(userNews.getNews())) {
					news.setId(userNews.getNews().getId());
					news.setSaved(true);
					break;
				}
			}
		}
	}
	
	public void saveNews(News news) {
		UserNews userNews = null;
		User user = getUser();
		
		News newsDb = newsRepository.findOneByUrl(news.getUrl());
		
		if (newsDb == null) {
			newsRepository.save(news);
			userNews = new UserNews(user, news);
		} else {
			userNews = new UserNews(user, newsDb);
		}
				
		userNewsService.save(userNews);
	}

	public void removeSavedNews(Long newsId) {
		User user = getUser();
		
		UserNews userNewsDb = userNewsService.getOneByUserIdAndNewsId(user.getId(), newsId);
		userNewsService.delete(userNewsDb);
	}
	
	private User getUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getByEmail(userDetails.getUsername());
		return user;
	}
}
