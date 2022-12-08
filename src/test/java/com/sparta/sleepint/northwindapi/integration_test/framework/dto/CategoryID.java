package com.sparta.sleepint.northwindapi.integration_test.framework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryID{

	@JsonProperty("description")
	private String description;

	@JsonProperty("id")
	private int id;

	@JsonProperty("categoryName")
	private String categoryName;

	@JsonProperty("picture")
	private String picture;

	@JsonProperty("hibernateLazyInitializer")
	private HibernateLazyInitializer hibernateLazyInitializer;

	public String getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public String getPicture(){
		return picture;
	}

	public HibernateLazyInitializer getHibernateLazyInitializer(){
		return hibernateLazyInitializer;
	}
}