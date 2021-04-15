package com.handpicked.demo.integration;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.handpicked.demo.domain.Category;
import com.handpicked.demo.repository.CategoryRepository;

@SpringBootTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testFindAll() {
		List<Category> allCategories = categoryRepository.findAllByOrderByNameAsc();
		assertThat(allCategories.size()).isEqualTo(7);
	}
}
