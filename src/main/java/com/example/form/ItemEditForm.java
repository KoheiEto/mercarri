package com.example.form;

import javax.validation.constraints.NotNull;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ItemEditForm {

	@NotBlank(message = "error:may not be empty")
	private String name;
	@NotBlank(message = "error:may not be empty")
	private String price;
	private String parentCategoryName;
	private String childCategoryName;
	private String grandchildCategoryName;
	/* @NotBlank(message = "error:may not be empty") */
	private String brand;
	@NotNull(message = "error:may not be empty") 
	private Integer condition;
	@NotBlank(message = "error:may not be empty")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	public String getChildCategoryName() {
		return childCategoryName;
	}

	public void setChildCategoryName(String childCategoryName) {
		this.childCategoryName = childCategoryName;
	}

	public String getGrandchildCategoryName() {
		return grandchildCategoryName;
	}

	public void setGrandchildCategoryName(String grandchildCategoryName) {
		this.grandchildCategoryName = grandchildCategoryName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
