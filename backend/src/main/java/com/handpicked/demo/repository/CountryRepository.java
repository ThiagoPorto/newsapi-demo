package com.handpicked.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.handpicked.demo.domain.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
	
	public List<Country> findAllByOrderByNameAsc();
	
}
