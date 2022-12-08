package com.sparta.sleepint.northwindapi.integration_test.framework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierID{

	@JsonProperty("country")
	private String country;

	@JsonProperty("contactTitle")
	private String contactTitle;

	@JsonProperty("address")
	private String address;

	@JsonProperty("city")
	private String city;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("contactName")
	private String contactName;

	@JsonProperty("companyName")
	private String companyName;

	@JsonProperty("postalCode")
	private String postalCode;

	@JsonProperty("id")
	private int id;

	@JsonProperty("region")
	private Object region;

	@JsonProperty("fax")
	private Object fax;

	@JsonProperty("homePage")
	private Object homePage;

	public String getCountry(){
		return country;
	}

	public String getContactTitle(){
		return contactTitle;
	}

	public String getAddress(){
		return address;
	}

	public String getCity(){
		return city;
	}

	public String getPhone(){
		return phone;
	}

	public String getContactName(){
		return contactName;
	}

	public String getCompanyName(){
		return companyName;
	}

	public String getPostalCode(){
		return postalCode;
	}

	public int getId(){
		return id;
	}

	public Object getRegion(){
		return region;
	}

	public Object getFax(){
		return fax;
	}

	public Object getHomePage(){
		return homePage;
	}
}