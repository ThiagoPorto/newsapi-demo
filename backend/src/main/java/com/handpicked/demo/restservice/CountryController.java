package com.handpicked.demo.restservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.handpicked.demo.domain.Country;
import com.handpicked.demo.service.CountryService;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@GetMapping("/countries")
	public List<Country> getAllCountries() {
		
		return this.countryService.getAll();
	}
}
