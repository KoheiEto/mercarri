package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Category;
import com.example.service.ItemEditService;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
	@Autowired
	ItemEditService itemEditService;

	@RequestMapping("/setUpCategoryName")
	public List<String> findByChildNameByParentName(String parentName) {

		// 親カテゴリ名からidを取得
		Category parentCategoryId = itemEditService.findIdByParentName(parentName);
		// 選択された親カテゴリのidの子カテゴリ名
		List<Category> childNameList = itemEditService.findByChildNameByParentId(parentCategoryId.getId());
		// 子カテゴリ名のリストをデータベースから取得した値から生成
		List<String> childList = childNameList.stream().map(category -> category.getName())
				.collect(Collectors.toList());

		return childList;
	}

	@RequestMapping("/setUpGrandName")
	public List<String> findGrandChildListByParentName(String parentName) {

		//// 子リスト(たくさん)の名前のidを取得

		// 親カテゴリ名からidを取得
		Category parentCategoryId = itemEditService.findIdByParentName(parentName);
		// 選択された親カテゴリのidの子カテゴリ名
		List<Category> childNameList = itemEditService.findByChildNameByParentId(parentCategoryId.getId());
		// 子カテゴリ名のリストをデータベースから取得した値から生成
		List<String> childList = childNameList.stream().map(category -> category.getName())
				.collect(Collectors.toList());

		List<Integer> idi = new ArrayList<>();
		/*
		 * for (String child : childList) {
		 * 
		 * Category childCategory = itemEditService.findChildCategory(child);
		 * idi.add(childCategory.getId()); }
		 */

		for (int i = 0; i < childList.size(); i++) {
			Category childCategory = itemEditService.findChildCategory(childList.get(i), parentCategoryId.getId());
			idi.add(childCategory.getId());
		}

		List<Category> grand = new ArrayList<>();
		//// そのidをparentとしてもつ孫カテゴリを取得
		/*
		 * for (Integer id : idi) { Category category =
		 * itemEditService.findGrandChildCategory(idi.get(0)); grand.add(category); }
		 */

		for (int i = 0; i < idi.size(); i++) {
			Category category = itemEditService.findGrandChildCategory(idi.get(i));
			grand.add(category);
		}

		List<String> grandChildList = grand.stream().map(category -> category.getName()).collect(Collectors.toList());

		return grandChildList;

	}

	@RequestMapping("/setUpGrandChildCategoryName")
	public List<String> findGrandChildaNameByChildId(String childName) {
		// 子カテゴリからidを取得
		Category childCategoryId = itemEditService.findIdByChildName(childName);
		// 選択された子カテゴリのidの孫カテゴリ名
		List<Category> grandChildNameList = itemEditService.findGrandChildNameBychildId(childCategoryId.getId());
		// 孫カテゴリ名のリストをデータベースから取得した値から生成
		List<String> grandChildList = grandChildNameList.stream().map(category -> category.getName())
				.collect(Collectors.toList());

		return grandChildList;
	}

}
