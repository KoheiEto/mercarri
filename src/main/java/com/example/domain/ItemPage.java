package com.example.domain;

import java.util.List;

public class ItemPage extends Item {
	private List<ItemCategoryJoin> itemList;
	// ページング関連
	private List<String> parentlinkName;
	private List<String> childlinkName;
	private List<String> grandChildlinkName;

	private Integer size;
	private Integer page;
	private Integer pageSize;
	private Integer totalPage;
	/*
	 * private List<String> parentlinkName; private List<String> childlinkName;
	 * private List<String> grandchildlinkName;
	 */

	/*
	 * public ItemPage(String name, String brand, String parent, String child,
	 * String grandChild, Integer page) { super(name, brand, parent, child,
	 * grandChild); this.page = page; }
	 */

	public ItemPage() {
	}

	public List<ItemCategoryJoin> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemCategoryJoin> itemList) {
		this.itemList = itemList;
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

	public List<String> getGrandChildlinkName() {
		return grandChildlinkName;
	}

	public void setGrandChildlinkName(List<String> grandChildlinkName) {
		this.grandChildlinkName = grandChildlinkName;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	/*
	 * public List<String> getParentlinkName() { return parentlinkName; }
	 * 
	 * public void setParentlinkName(List<String> parentlinkName) {
	 * this.parentlinkName = parentlinkName; }
	 * 
	 * public List<String> getChildlinkName() { return childlinkName; }
	 * 
	 * public void setChildlinkName(List<String> childlinkName) { this.childlinkName
	 * = childlinkName; }
	 * 
	 * public List<String> getGrandchildlinkName() { return grandchildlinkName; }
	 * 
	 * public void setGrandchildlinkName(List<String> grandchildlinkName) {
	 * this.grandchildlinkName = grandchildlinkName; }
	 */

}