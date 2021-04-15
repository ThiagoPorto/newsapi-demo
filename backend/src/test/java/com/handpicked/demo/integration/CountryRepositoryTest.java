package com.handpicked.demo.integration;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.handpicked.demo.domain.Country;
import com.handpicked.demo.repository.CountryRepository;

@SpringBootTest
public class CountryRepositoryTest {

	@Autowired
	private CountryRepository countryRepository;
	
	@Test
	public void testFindAll() {
		List<Country> allCountries = countryRepository.findAllByOrderByNameAsc();
		assertThat(allCountries.size()).isEqualTo(54);
	}
}
