package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.repository.ItemEditRepository;

@Service
public class ItemEditService {
	@Autowired
	ItemEditRepository itemEditRepository;

	// 親リスト取得
	public List<Category> findByParentName() {
		List<Category> categories = itemEditRepository.findByParentName();
		return categories;
	}

	// 子リスト取得
	public List<Category> findByChildName() {
		List<Category> categories = itemEditRepository.findByChildName();
		return categories;
	}

	// 孫リスト取得
	public List<Category> findByGrandChildtName() {
		List<Category> categories = itemEditRepository.findByGrandChildName();
		return categories;
	}

	// 親カテゴリからidを取得
	public Category findIdByParentName(String parentName) {
		Category category = itemEditRepository.findIdByParentName(parentName);
		return category;
	}

	// 子カテゴリからidを取得
	public Category findIdByChildName(String childName) {
		Category category = itemEditRepository.findIdByChildName(childName);
		return category;
	}

	// 親カテゴリの名前から子カテゴリの名前リストを取得
	public List<Category> findByChildNameByParentId(Integer parentId) {
		List<Category> category = itemEditRepository.findByChildNameByParentId(parentId);
		return category;
	}

	// 子カテゴリの名前から子カテゴリの名前リストを取得
	public List<Category> findGrandChildNameBychildId(Integer childId) {
		List<Category> category = itemEditRepository.findGrandChildNameByChildId(childId);
		return category;
	}

	public List<Category> findChildNameByParentId(Integer parentId) {
		List<Category> category = itemEditRepository.findChildNameByParentId(parentId);
		return category;
	}

	public Category findIdByParentId(Integer grandChildId) {
		Category category = itemEditRepository.findIdByParentId(grandChildId);
		return category;
	}

	// 選択したidの孫カテゴリのリスト
	public List<Category> findGrandChildList(Integer childId) {
		List<Category> category = itemEditRepository.findGrandChildList(childId);
		return category;
	}

	public Category findCategoryIdByCategoryName(String categoryName) {
		Category category = itemEditRepository.findCategoryIdByCategoryName(categoryName);
		return category;
	}

	//
	public Category findParentByParentName(String parentName) {
		Category category = itemEditRepository.findParentByParentName(parentName);
		return category;
	}

	///
	public List<Category> findChildList(Integer parentId) {
		List<Category> category = itemEditRepository.findChildList(parentId);
		return category;
	}

	// 編集したitem情報を更新
	public void editInsert(Item item) {
		itemEditRepository.editInsert(item);
	}

	// 子カテゴリ名前をもつレコードを取得
	public Category findChildCategory(String childName, Integer id) {
		Category category = itemEditRepository.findChildCategory(childName, id);
		return category;

	}

	// そのidをparentとしてもつ孫カテゴリを取得
	public Category findGrandChildCategory(Integer id) {
		Category category = itemEditRepository.findGrandChildCategory(id);
		return category;
	}

}
