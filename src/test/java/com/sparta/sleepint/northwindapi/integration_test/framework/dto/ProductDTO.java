package com.sparta.sleepint.northwindapi.integration_test.framework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {

	@JsonProperty("unitPrice")
	private int unitPrice;

	@JsonProperty("unitsInStock")
	private int unitsInStock;

	@JsonProperty("reorderLevel")
	private int reorderLevel;

	@JsonProperty("supplierID")
	private SupplierID supplierID;

	@JsonProperty("id")
	private int id;

	@JsonProperty("quantityPerUnit")
	private String quantityPerUnit;

	@JsonProperty("discontinued")
	private boolean discontinued;

	@JsonProperty("productName")
	private String productName;

	@JsonProperty("unitsOnOrder")
	private int unitsOnOrder;

	@JsonProperty("categoryID")
	private CategoryID categoryID;

	public int getUnitPrice(){
		return unitPrice;
	}

	public int getUnitsInStock(){
		return unitsInStock;
	}

	public int getReorderLevel(){
		return reorderLevel;
	}

	public SupplierID getSupplierID(){
		return supplierID;
	}

	public int getId(){
		return id;
	}

	public String getQuantityPerUnit(){
		return quantityPerUnit;
	}

	public boolean isDiscontinued(){
		return discontinued;
	}

	public String getProductName(){
		return productName;
	}

	public int getUnitsOnOrder(){
		return unitsOnOrder;
	}

	public CategoryID getCategoryID(){
		return categoryID;
	}

	@Override
	public String toString() {
		return "ProductDTO{" +
				"id=" + id +
				", unitPrice=" + unitPrice +
				", unitsInStock=" + unitsInStock +
				", reorderLevel=" + reorderLevel +
				", supplierID=" + supplierID +
				", quantityPerUnit='" + quantityPerUnit + '\'' +
				", discontinued=" + discontinued +
				", productName='" + productName + '\'' +
				", unitsOnOrder=" + unitsOnOrder +
				", categoryID=" + categoryID +
				'}';
	}
}