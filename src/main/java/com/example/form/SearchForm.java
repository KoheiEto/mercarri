package com.example.form;

import javax.validation.constraints.NotBlank;

public class SearchForm {
	@NotBlank(message = "error:may not be empty")
	private String name = "";
	@NotBlank(message = "error:may not be empty")
	private String parentCategoryName = "";
	@NotBlank(message = "error:may not be empty")
	private String childCategoryName = "";
	@NotBlank(message = "error:may not be empty")
	private String grandchildCategoryName = "";
	@NotBlank(message = "error:may not be empty")
	private String brand = "";
	private Integer page = 1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
