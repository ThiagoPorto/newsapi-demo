package com.handpicked.demo.payload;

import java.util.List;

import com.handpicked.demo.domain.News;

public class NewsAPIResponse {

	private String status;
	private Long totalResults;
	private List<News> articles;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}
	public List<News> getArticles() {
		return articles;
	}
	public void setArticles(List<News> articles) {
		this.articles = articles;
	}
}
