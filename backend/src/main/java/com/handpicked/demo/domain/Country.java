package com.handpicked.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", unique=true)
	private String name;
	
	@Column(name = "initials", unique=true)
	private String initials;

	public Country() {
		super();
	}

	public Country(String name, String initials) {
		super();
		this.name = name;
		this.initials = initials;
	}

	public Country(Long id, String name, String initials) {
		super();
		this.id = id;
		this.name = name;
		this.initials = initials;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}
}
