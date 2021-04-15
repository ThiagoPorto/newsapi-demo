package com.handpicked.demo.restservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.handpicked.demo.domain.Category;
import com.handpicked.demo.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public List<Category> getAllCountries() {
		
		return this.categoryService.getAll();
	}
}
