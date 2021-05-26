package com.example.domain;

import java.util.List;

public class ItemCategoryJoin {
	private Integer id;
	private String name;
	private Integer condition;
	private Integer category;
	private String brand;
	private double price;
	private Integer shipping;
	private String description;
	private Integer categoryId;
	private Integer parent;
	private String categoryName;
	private String nameAll;
	private List<String> parentlinkName;
	private List<String> childlinkName;
	private List<String> grandchildlinkName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	public List<String> getParentlinkName() {
		return parentlinkName;
	}

	public void setParentlinkName(List<String> parentlinkName) {
		this.parentlinkName = parentlinkName;
	}

	public List<String> getChildlinkName() {
		return childlinkName;
	}

	public void setChildlinkName(List<String> childlinkName) {
		this.childlinkName = childlinkName;
	}

	public List<String> getGrandchildlinkName() {
		return grandchildlinkName;
	}

	public void setGrandchildlinkName(List<String> grandchildlinkName) {
		this.grandchildlinkName = grandchildlinkName;
	}

}
