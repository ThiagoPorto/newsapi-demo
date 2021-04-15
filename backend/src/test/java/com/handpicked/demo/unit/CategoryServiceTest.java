package com.handpicked.demo.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.handpicked.demo.domain.Category;
import com.handpicked.demo.repository.CategoryRepository;
import com.handpicked.demo.service.CategoryService;

@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	@Test
	public void testGetAll() {
		when(categoryRepository.findAllByOrderByNameAsc())
			.thenReturn(Stream.of(new Category("Category 1"), new Category("Category 2"), new Category("Category 3"))
					.collect(Collectors.toList()));
		assertThat(categoryService.getAll().size()).isEqualTo(3);
	}
	
}
