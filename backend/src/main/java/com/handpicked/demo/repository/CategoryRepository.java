package com.handpicked.demo.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.handpicked.demo.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	public List<Category> findAllByOrderByNameAsc();
	
}
