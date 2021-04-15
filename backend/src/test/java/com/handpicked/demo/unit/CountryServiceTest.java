package com.handpicked.demo.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.handpicked.demo.domain.Country;
import com.handpicked.demo.repository.CountryRepository;
import com.handpicked.demo.service.CountryService;

@SpringBootTest
public class CountryServiceTest {

	@Autowired
	private CountryService countryService;
	
	@MockBean
	private CountryRepository countryRepository;
	
	@Test
	public void testGetAll() {
		when(countryRepository.findAllByOrderByNameAsc())
			.thenReturn(Stream.of(new Country("USA", "us"), new Country("Brazil", "br"), new Country("Canada", "ca"))
					.collect(Collectors.toList()));
		assertThat(countryService.getAll().size()).isEqualTo(3);
	}
}
