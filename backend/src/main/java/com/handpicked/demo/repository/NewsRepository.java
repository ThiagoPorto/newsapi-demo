package com.handpicked.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.handpicked.demo.domain.News;

public interface NewsRepository extends CrudRepository<News, Long> {
	public News findOneByUrl(String url);
}
