package com.handpicked.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.handpicked.demo.domain.Category;
import com.handpicked.demo.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAll() {
		return categoryRepository.findAllByOrderByNameAsc();
	}
}
