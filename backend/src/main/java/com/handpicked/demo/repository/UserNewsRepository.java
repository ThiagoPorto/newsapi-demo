package com.handpicked.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.handpicked.demo.domain.News;
import com.handpicked.demo.domain.UserNews;

public interface UserNewsRepository extends CrudRepository<UserNews, Long> {
	public List<UserNews> findByUserId(Long userId);
	public UserNews findOneByUserIdAndNewsId(Long userId, Long newsId);
	
	@Query("select un.news from UserNews un where un.user.id = :userId order by un.id desc")
	public List<News> findNewsByUserId(@Param("userId") Long userId, Pageable pageable);
	
	@Query("select count(un.news) from UserNews un where un.user.id = :userId")
	public Long countNewsByUserId(@Param("userId") Long userId);
}
