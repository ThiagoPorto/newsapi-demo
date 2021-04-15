package com.handpicked.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handpicked.demo.domain.Country;
import com.handpicked.demo.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> getAll() {
		return countryRepository.findAllByOrderByNameAsc();
	}

}
