package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.domain.ItemCategoryJoin;
import com.example.domain.ItemPage;
import com.example.form.SearchForm;
import com.example.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository itemRepository;

	private static final Integer PAGE_SIZE = 30;

	public List<ItemCategoryJoin> findAll() {
		List<ItemCategoryJoin> itemCategoryJoins = itemRepository.findAll();
		return itemCategoryJoins;
	}

	public ItemCategoryJoin findById(Integer id) {
		ItemCategoryJoin itemCategoryJoin = itemRepository.findById(id);
		return itemCategoryJoin;
	}

	// item追加
	public void itemInsert(Item item) {
		itemRepository.itemInsert(item);
	}

	// 検索されたitem一覧
	public ItemPage itemBysearch(SearchForm searchForm, List<String> categoryId, Integer page) {

		ItemPage iPage = itemRepository.itemBysearch(searchForm, categoryId, page);

		return iPage;
	}

	// 親子
	public List<Category> findParentChild(String fullName) {
		List<Category> category = itemRepository.findParentChild(fullName);
		return category;
	}

	// 子孫
	public List<Category> findChildGrandChild(String fullName) {
		List<Category> category = itemRepository.findchildGrandchild(fullName);
		return category;
	}

	// 親孫
	public List<Category> findparentGrandChild(String parent, String grandChild) {
		List<Category> category = itemRepository.findparentGrandChild(parent, grandChild);
		return category;
	}

	// 親
	public List<Category> findparent(String parent) {
		List<Category> category = itemRepository.findparent(parent);
		return category;
	}

	// 子
	public List<Category> findchild1(String child) {
		List<Category> category = itemRepository.findparent(child);
		return category;
	}

	// 孫
	public List<Category> findgrandChild(String grandChild) {
		List<Category> category = itemRepository.findGrandChild(grandChild);
		return category;
	}
}
