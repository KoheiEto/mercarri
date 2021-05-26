package com.example.controller;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.domain.ItemCategoryJoin;
import com.example.domain.ItemPage;
import com.example.domain.NameAll;
import com.example.domain.User;
import com.example.form.ItemAddForm;
import com.example.form.ItemEditForm;
import com.example.form.RegisterForm;
import com.example.form.SearchForm;
import com.example.service.ItemEditService;
import com.example.service.ItemService;
import com.example.service.UserService;

@Controller
@RequestMapping("")
public class MerController {

	@Autowired
	private HttpSession session;
	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemEditService itemEditService;

	@ModelAttribute
	public RegisterForm setUpRegisterForm() {
		return new RegisterForm();
	}

	@ModelAttribute
	public ItemEditForm setUpItemEditForm() {
		return new ItemEditForm();
	}

	@ModelAttribute
	public ItemAddForm setUpItemAddForm() {
		return new ItemAddForm();
	}

	@ModelAttribute
	public SearchForm setUpSearchForm() {
		return new SearchForm();
	}

	// item一覧表示,検索,ページング
	@RequestMapping("")
	public String index(SearchForm searchForm, BindingResult result, Model model) {

		// 不正な値のチェック
		Integer page = searchForm.getPage();
		Integer totalPage = (Integer) session.getAttribute("totalPage");
		if (isNull(page) || page <= 0 || isNull(totalPage)) {
			page = 1;
		} else if (page > totalPage) {
			page = totalPage;
		}

		// 検索条件の設定
		/*
		 * ItemPage item = new ItemPage(form.getItemName(), form.getBrand(),
		 * form.getParentCategory(), form.getChildCategory(),
		 * form.getGrandChildCategory(), page); ItemPage itemPage =
		 * service.showAllItems(item);
		 * 
		 * model.addAttribute("searchForm", form); model.addAttribute("itemList",
		 * itemPage.getItemList()); model.addAttribute("size", itemPage.getSize());
		 * model.addAttribute("page", itemPage.getPage());
		 * model.addAttribute("totalPage", itemPage.getTotalPage()); // 表示用
		 * session.setAttribute("totalPage", itemPage.getTotalPage()); // サーバー用
		 */
		/*
		 * if (result.hasErrors()) { return "redirect:/"; }
		 */

		/*
		 * //////////////// ajax/////////// // 親カテゴリのリスト List<Category> categories =
		 * itemEditService.findByParentName(); model.addAttribute("categories",
		 * categories); // 選択してidの詳細表示用のitem情報 ItemCategoryJoin itemCategoryJoins =
		 * itemService.findById(1200001);
		 * 
		 * model.addAttribute("itemCategoryJoins", itemCategoryJoins);
		 * 
		 * // カテゴリリストから親，子，孫のリストを作成 String nameAll = itemCategoryJoins.getNameAll();
		 * String parentName = Arrays.asList(nameAll.split("/")).get(0); String
		 * childName = Arrays.asList(nameAll.split("/")).get(1); String grandChildName =
		 * Arrays.asList(nameAll.split("/")).get(2); model.addAttribute("parentName",
		 * parentName); model.addAttribute("childName1", childName);
		 * model.addAttribute("grandChildName1", grandChildName);
		 */

		/*
		 * /////// Barbieの子カテゴリリスト Category category =
		 * itemEditService.findParentByParentName(parentName); Integer parentId =
		 * category.getId();
		 * 
		 * List<Category> childList = itemEditService.findChildList(parentId);
		 * 
		 * List<String> childNameList = childList.stream().map(categoryy ->
		 * categoryy.getName()) .collect(Collectors.toList()); // Beautyの子カテゴリリスト
		 * model.addAttribute("childNameList", childNameList);
		 */

		/* 親カテゴリのリスト */
		List<Category> categories = itemEditService.findByParentName();
		model.addAttribute("categories", categories);
		// すべての子カテゴリ
		List<Category> childNameList1 = itemEditService.findByChildName();
		List<String> childNameListList1 = childNameList1.stream().map(categoryy -> categoryy.getName())
				.collect(Collectors.toList());
		model.addAttribute("childNameListList1", childNameListList1);
		// すべての孫カテゴリ
		List<Category> grandChildNameList1 = itemEditService.findByGrandChildtName();
		List<String> grandChildNameListList1 = grandChildNameList1.stream().map(categoryy -> categoryy.getName())
				.collect(Collectors.toList());
		model.addAttribute("grandChildNameListList1", grandChildNameListList1);

		/*
		 * /////// Barbieの孫カテゴリリスト
		 * 
		 * // 選択したidの孫カテゴリ名 Integer grandChildNameById =
		 * itemCategoryJoins.getCategoryId(); // 上の孫カテゴリIdのparentId(子カテゴリID)を取得 Category
		 * category1 = itemEditService.findIdByParentId(grandChildNameById); Integer
		 * childId = category1.getParent(); //
		 * 取得した子カテゴリIDをparentId(親カテゴリID)としてもつカテゴリのリストを取得((孫カテゴリのリスト)) List<Category>
		 * grandChildList = itemEditService.findGrandChildList(childId); // 孫カテゴリのリストを生成
		 * 
		 * List<String> grandChildNameList = grandChildList.stream().map(categoryyy ->
		 * categoryyy.getName()) .collect(Collectors.toList()); // idの孫カテゴリリスト
		 * model.addAttribute("grandChildNameList", grandChildNameList);
		 */

		//// 名前で検索

		//// 検索する(全部条件あり)/////////
		/* List<ItemCategoryJoin> itemList = new ArrayList<>(); */
		ItemPage iPage = new ItemPage();
		List<String> categoryId = new ArrayList<String>();

		final String parent = searchForm.getParentCategoryName();
		final String child = searchForm.getChildCategoryName();
		final String grandChild = searchForm.getGrandchildCategoryName();

		// //カテゴリ未選択
		if (("".equals(parent)) && ("".equals(child)) && ("".equals(grandChild))) {

			categoryId = null;

			// カテゴリ全選択
		} else if ((!("".equals(parent))) && (!("".equals(child))) && (!("".equals(grandChild)))) {

			////// カテゴリテーブルからカテゴリidを取得
			String parentName1 = searchForm.getParentCategoryName().concat("/");
			String childName1 = searchForm.getChildCategoryName().concat("/");
			String grandChildName1 = searchForm.getGrandchildCategoryName();
			String fullCategoryName = parentName1.concat(childName1.concat(grandChildName1));

			// 上のカテゴリ名でカテゴリIDを取得
			Category category11 = itemEditService.findCategoryIdByCategoryName(fullCategoryName);

			// カテゴリidを取得
			Integer categoryNameIdId = category11.getId();
			String categoryNameIdd = String.valueOf(categoryNameIdId);
			categoryId.add(categoryNameIdd);

		}
		// カテゴリ親子
		else if ((!("".equals(parent))) && (!("".equals(child))) && (("".equals(grandChild)))) {

			String fullName = parent.concat("/").concat(child);

			List<Category> category = itemService.findParentChild(fullName);

			List<String> parentChildList = category.stream().map(cate -> String.valueOf(cate.getId()))
					.collect(Collectors.toList());

			categoryId = parentChildList;

		}
		// カテゴリ子孫
		else if ((("".equals(parent))) && (!("".equals(child))) && (!("".equals(grandChild)))) {

			String fullName = child.concat("/").concat(grandChild);

			List<Category> category = itemService.findChildGrandChild(fullName);

			List<String> childGrandChildList = category.stream().map(cate -> String.valueOf(cate.getId()))
					.collect(Collectors.toList());

			categoryId = childGrandChildList;
		}
		// カテゴリ親孫
		else if ((!("".equals(parent))) && (("".equals(child))) && (!("".equals(grandChild)))) {

			List<Category> category = itemService.findparentGrandChild(parent, grandChild);

			List<String> parentGrandChildList = category.stream().map(cate -> String.valueOf(cate.getId()))
					.collect(Collectors.toList());

			categoryId = parentGrandChildList;
		}
		// カテゴリ親
		else if ((!("".equals(parent))) && (("".equals(child))) && (("".equals(grandChild)))) {
			List<Category> category = itemService.findparent(parent);
			List<String> parentList = category.stream().map(cate -> String.valueOf(cate.getId()))
					.collect(Collectors.toList());
			categoryId = parentList;
		}
		// カテゴリ子
		else if ((!("".equals(parent))) && (("".equals(child))) && (!("".equals(grandChild)))) {
			System.out.println("k");
			List<Category> category = itemService.findchild1(child);

		}
		// カテゴリ孫
		else if ((("".equals(parent))) && (("".equals(child))) && (!("".equals(grandChild)))) {
			List<Category> category = itemService.findgrandChild(grandChild);
			List<String> grandChildtList = category.stream().map(cate -> String.valueOf(cate.getId()))
					.collect(Collectors.toList());
			categoryId = grandChildtList;
		}

		////// カテゴリid、名前、ブランドでデータを取得する

		/* itemList = itemService.itemBysearch(searchForm, categoryId); */
		iPage = itemService.itemBysearch(searchForm, categoryId, page);

		List<ItemCategoryJoin> itemList = new ArrayList<>();
		// iPageのリストをリストへ
		for (ItemCategoryJoin item : iPage.getItemList()) {
			itemList.add(item);
		}
		List<String> linkName = new ArrayList<>();
		// itemListをStringのリストへ
		for (ItemCategoryJoin item : itemList) {
			linkName.add(item.getNameAll());
		}
		List<String> parentlinkName = new ArrayList<>();
		List<String> childlinkName = new ArrayList<>();
		List<String> grandChildlinkName = new ArrayList<>();
		for (String i : linkName) {
			parentlinkName.add(Arrays.asList(i.split("/")).get(0));
			childlinkName.add(Arrays.asList(i.split("/")).get(1));
			grandChildlinkName.add(Arrays.asList(i.split("/")).get(2));
		}

		/*
		 * iPage.getItemList().add((ItemCategoryJoin) parentlinkName);
		 * iPage.getItemList().add((ItemCategoryJoin) childlinkName);
		 * iPage.getItemList().add((ItemCategoryJoin) grandChildlinkName);
		 */

		iPage.setParentlinkName(parentlinkName);
		iPage.setParentlinkName(childlinkName);
		iPage.setParentlinkName(grandChildlinkName);

		model.addAttribute("parentlinkName", parentlinkName);
		model.addAttribute("childlinkName", childlinkName);
		model.addAttribute("grandChildlinkName", grandChildlinkName);

		/*
		 * iPage.setParentlinkName(parentlinkName);
		 * iPage.setChildlinkName(childlinkName);
		 * iPage.setGrandchildlinkName(grandChildlinkName);
		 * 
		 * model.addAttribute("itemCategoryJoins", iPage.getParentlinkName());
		 * model.addAttribute("itemCategoryJoins", iPage.getChildlinkName());
		 * model.addAttribute("itemCategoryJoins", iPage.getGrandchildlinkName());
		 */

		model.addAttribute("searchForm", searchForm);
		model.addAttribute("itemCategoryJoins", iPage.getItemList());
		model.addAttribute("size", iPage.getSize());
		model.addAttribute("page", iPage.getPage());
		model.addAttribute("totalPage", iPage.getTotalPage());
		session.setAttribute("totalPage", iPage.getTotalPage()); // サーバー用

		return "list";
	}

	// item追加画面表示
	@RequestMapping("/add")
	public String add(Model model) {
		// 親カテゴリのリスト
		List<Category> categories = itemEditService.findByParentName();
		model.addAttribute("categories", categories);

		// 選択してidの詳細表示用のitem情報
		ItemCategoryJoin itemCategoryJoins = itemService.findById(1200001);

		model.addAttribute("itemCategoryJoins", itemCategoryJoins);

		// カテゴリリストから親，子，孫のリストを作成
		String nameAll = itemCategoryJoins.getNameAll();
		String parentName = Arrays.asList(nameAll.split("/")).get(0);
		String childName = Arrays.asList(nameAll.split("/")).get(1);
		String grandChildName = Arrays.asList(nameAll.split("/")).get(2);
		model.addAttribute("parentName", parentName);
		model.addAttribute("childName1", childName);
		model.addAttribute("grandChildName1", grandChildName);

		/////// Barbieの子カテゴリリスト
		Category category = itemEditService.findParentByParentName(parentName);
		Integer parentId = category.getId();

		List<Category> childList = itemEditService.findChildList(parentId);

		List<String> childNameList = childList.stream().map(categoryy -> categoryy.getName())
				.collect(Collectors.toList());
		// Beautyの子カテゴリリスト
		model.addAttribute("childNameList", childNameList);

		/////// Barbieの孫カテゴリリスト

		// 選択したidの孫カテゴリ名
		Integer grandChildNameById = itemCategoryJoins.getCategoryId();
		// 上の孫カテゴリIdのparentId(子カテゴリID)を取得
		Category category1 = itemEditService.findIdByParentId(grandChildNameById);
		Integer childId = category1.getParent();
		// 取得した子カテゴリIDをparentId(親カテゴリID)としてもつカテゴリのリストを取得((孫カテゴリのリスト))
		List<Category> grandChildList = itemEditService.findGrandChildList(childId);
		// 孫カテゴリのリストを生成

		List<String> grandChildNameList = grandChildList.stream().map(categoryyy -> categoryyy.getName())
				.collect(Collectors.toList());
		// idの孫カテゴリリスト
		model.addAttribute("grandChildNameList", grandChildNameList);

		Map<Integer, Integer> conditionList = new LinkedHashMap<>();
		conditionList.put(1, 1);
		conditionList.put(2, 2);
		conditionList.put(3, 3);

		model.addAttribute("conditionList", conditionList);

		return "add";
	}

	// itemを追加する
	@RequestMapping("/adding")
	public String adding(@Validated ItemAddForm itemAddForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return add(model);
		}

		String parentName = itemAddForm.getParentCategoryName().concat("/");
		String childName = itemAddForm.getChildCategoryName().concat("/");
		String grandChildName = itemAddForm.getGrandchildCategoryName();

		String fullCategoryName = parentName.concat(childName.concat(grandChildName));

		// 上のカテゴリ名でカテゴリIDを取得
		Category category = itemEditService.findCategoryIdByCategoryName(fullCategoryName);
		// カテゴリID取得
		Integer categoryNameId = category.getId();

		// フォームからドメインへ
		Item item = new Item();
		item.setName(itemAddForm.getName());
		item.setCondition(itemAddForm.getCondition());
		item.setCategory(categoryNameId);
		item.setBrand(itemAddForm.getBrand());
		item.setPrice(Double.parseDouble(itemAddForm.getPrice()));
		item.setShipping(0);
		item.setDescription(itemAddForm.getDescription());
		itemService.itemInsert(item);

		return "redirect:/list";
	}

	// item詳細表示
	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		ItemCategoryJoin itemCategoryJoins = itemService.findById(id);
		model.addAttribute("itemCategoryJoins", itemCategoryJoins);
		return "detail";
	}

	// item編集画面表示
	@RequestMapping("/edit")
	public String edit(Integer id, Model model) {

		// 選択してidの詳細表示用のitem情報
		ItemCategoryJoin itemCategoryJoins = itemService.findById(id);

		// カテゴリリストから親，子，孫のリストを作成
		String nameAll = itemCategoryJoins.getNameAll();
		String parentName = Arrays.asList(nameAll.split("/")).get(0);
		String childName = Arrays.asList(nameAll.split("/")).get(1);
		String grandChildName = Arrays.asList(nameAll.split("/")).get(2);
		model.addAttribute("parentName1", parentName);
		model.addAttribute("childName1", childName);
		model.addAttribute("grandChildName1", grandChildName);

		// それぞれのカテゴリ名をセット
		NameAll name = new NameAll();
		name.setParentName(parentName);
		name.setChildName(childName);
		name.setGrandChildName(grandChildName);

		model.addAttribute("name", name);
		model.addAttribute("itemCategoryJoins", itemCategoryJoins);

		// 親カテゴリのリスト
		List<Category> categories = itemEditService.findByParentName();
		// 子カテゴリのリスト
		List<Category> categories2 = itemEditService.findByChildName();
		// 孫カテゴリのリスト
		List<Category> categories3 = itemEditService.findByGrandChildtName();

		// 選択したidの孫カテゴリ名
		Integer grandChildNameById = itemCategoryJoins.getCategoryId();

		// 上の孫カテゴリIdのparentId(子カテゴリID)を取得
		Category category1 = itemEditService.findIdByParentId(grandChildNameById);
		Integer childId = category1.getParent();
		// 取得した子カテゴリIDをparentId(親カテゴリID)としてもつカテゴリのリストを取得((孫カテゴリのリスト))
		List<Category> grandChildList = itemEditService.findGrandChildList(childId);
		// 孫カテゴリのリストを生成

		List<String> grandChildNameList = grandChildList.stream().map(category -> category.getName())
				.collect(Collectors.toList());
		// idの孫カテゴリリスト
		model.addAttribute("grandChildNameList", grandChildNameList);

		///////
		Category category = itemEditService.findParentByParentName(parentName);
		Integer parentId = category.getId();

		List<Category> childList = itemEditService.findChildList(parentId);

		List<String> childNameList = childList.stream().map(categoryy -> categoryy.getName())
				.collect(Collectors.toList());
		// idの子カテゴリリスト
		model.addAttribute("childNameList", childNameList);

		/*
		 * // 親カテゴリIDをparentIdとしてもつカテゴリ名のリストを取得((子カテゴリのリスト)) Integer parentId =
		 * category1.getParent(); // 取得したidを親idに持つカテゴリのリストを取得((子カテゴリのリスト))
		 * List<Category> ChildList = itemEditService.findChildNameByParentId(parentId);
		 * ChildList.get(0).getParent();
		 * 
		 * // 子カテゴリのリストを生成 List<String> ChildNameList = ChildList.stream().map(category
		 * -> category.getName()) .collect(Collectors.toList());
		 * model.addAttribute("ChildNameList", ChildNameList);
		 */

		Map<Integer, Integer> conditionList = new LinkedHashMap<>();
		conditionList.put(1, 1);
		conditionList.put(2, 2);
		conditionList.put(3, 3);

		model.addAttribute("conditionList", conditionList);

		model.addAttribute("categories", categories);
		model.addAttribute("categories2", categories2);
		model.addAttribute("categories3", categories3);
		return "edit";
	}

	// itemを編集する
	@RequestMapping("/editing")
	public String editing(@Validated ItemEditForm itemEditForm, BindingResult result, Model model, Integer id) {

		if (result.hasErrors()) {
			return edit(id, model);
		}

		String parentName = itemEditForm.getParentCategoryName().concat("/");
		String childName = itemEditForm.getChildCategoryName().concat("/");
		String grandChildName = itemEditForm.getGrandchildCategoryName();

		String fullCategoryName = parentName.concat(childName.concat(grandChildName));

		// 上のカテゴリ名でカテゴリIDを取得
		Category category = itemEditService.findCategoryIdByCategoryName(fullCategoryName);
		Integer categoryNameId = category.getId();
		// フォームからドメインへ
		Item item = new Item();
		item.setId(id);
		item.setName(itemEditForm.getName());
		item.setPrice(Double.parseDouble(itemEditForm.getPrice()));
		item.setCondition(itemEditForm.getCondition());
		item.setCategory(categoryNameId);
		item.setBrand(itemEditForm.getBrand());
		item.setDescription(itemEditForm.getDescription());

		itemEditService.editInsert(item);

		return detail(id, model);
	}

	// ユーザ情報登録画面を表示
	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	// ユーザ情報を登録
	@RequestMapping("/registering")
	public String registering(@Validated RegisterForm registerForm, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "register";
		}

		if (userService.existByEmail(registerForm.getEmail())) {
			model.addAttribute("emailResult", "そのメールアドレスは既に登録されています");
			return "register";
		}

		User user = new User();

		user.setMailAddress(registerForm.getEmail());
		user.setPassword(registerForm.getPassword());

		userService.insert(user);

		return "redirect:/list";
	}

	// ログイン・ログアウト
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/login")
	public String login() {
		return "list";
	}

}
