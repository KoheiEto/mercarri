package com.example.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.domain.ItemCategoryJoin;
import com.example.domain.ItemPage;
import com.example.domain.Original;
import com.example.domain.User;
import com.example.form.SearchForm;

@Repository
public class ItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<ItemCategoryJoin> CI_ROW_MAPPER = (rs, i) -> {
		ItemCategoryJoin itemCategoryJoin = new ItemCategoryJoin();
		itemCategoryJoin.setId(rs.getInt("id"));
		itemCategoryJoin.setName(rs.getString("name"));
		itemCategoryJoin.setCondition(rs.getInt("condition"));
		itemCategoryJoin.setCategory(rs.getInt("category"));
		itemCategoryJoin.setBrand(rs.getString("brand"));
		itemCategoryJoin.setPrice(rs.getDouble("price"));
		itemCategoryJoin.setShipping(rs.getInt("shipping"));
		itemCategoryJoin.setDescription(rs.getString("description"));
		itemCategoryJoin.setCategoryId(rs.getInt("category_id"));
		itemCategoryJoin.setParent(rs.getInt("parent"));
		itemCategoryJoin.setCategoryName(rs.getString("category_name"));
		itemCategoryJoin.setNameAll(rs.getString("name_all"));

		return itemCategoryJoin;
	};

	private static final RowMapper<Category> C_ROW_MAPPER = (rs, i) -> {
		Category category = new Category();
		category.setId(rs.getInt("category_id"));
		category.setParent(rs.getInt("parent"));
		category.setName(rs.getString("name"));
		category.setNameAll(rs.getString("name_all"));

		return category;
	};

	private static final RowMapper<Item> I_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setCondition(rs.getInt("condition"));
		item.setCategory(rs.getInt("category"));
		item.setBrand(rs.getString("brand"));
		item.setPrice(rs.getDouble("price"));
		item.setShipping(rs.getInt("shipping"));
		item.setDescription(rs.getString("description"));

		return item;
	};

	private static final RowMapper<Original> O_ROW_MAPPER = (rs, i) -> {
		Original original = new Original();
		original.setId(rs.getInt("id"));
		original.setName(rs.getString("name"));
		original.setConditionId(rs.getInt("condition_id"));
		original.setCategoryName(rs.getString("category_name"));
		original.setBrand(rs.getString("brand"));
		original.setPrice(rs.getDouble("price"));
		original.setDescription(rs.getString("description"));

		return original;
	};

	// item一覧表示用の情報をitemテーブルとcategoryテーブルから取得
	public List<ItemCategoryJoin> findAll() {
		String sql = "select i.id, i.name, i.condition, i.category, i.brand, i.price, i.shipping, i.description,\r\n"
				+ "	c.id as category_id, c.parent, c.name as category_name, c.name_all\r\n"
				+ "from items as i inner join category as c on i.category = c.id order by i.id limit 100";
		List<ItemCategoryJoin> itemCategoryJoins = template.query(sql, CI_ROW_MAPPER);
		return itemCategoryJoins;
	}

	// item詳細表示用の情報をidを指定してitemテーブルとcategoryテーブルから取得
	public ItemCategoryJoin findById(Integer id) {
		String sql = "select i.id, i.name, i.condition, i.category, i.brand, i.price, i.shipping, i.description,\r\n"
				+ "	c.id as category_id, c.parent, c.name as category_name, c.name_all\r\n"
				+ "from items as i inner join category as c on i.category = c.id where i.id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		ItemCategoryJoin itemCategoryJoin = template.queryForObject(sql, param, CI_ROW_MAPPER);
		return itemCategoryJoin;
	}

	// ユーザ登録
	public void insert(User user) {
		String sql = "insert into users (mail_address,password)values(:mailAddress,:password)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", user.getMailAddress())
				.addValue("password", user.getPassword());
		template.update(sql, param);
	}

	// item追加
	public void itemInsert(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);

		String sql = "insert into items (name, condition, category, brand, price, shipping, description)values"
				+ "(:name, :condition, :category, :brand, :price, :shipping, :description)";

		/*
		 * SqlParameterSource paramm = new MapSqlParameterSource().addValue("name",
		 * item.getName()) .addValue("condition",
		 * item.getCondition()).addValue("category", item.getCategory())
		 * .addValue("brand", item.getBrand()).addValue("price", item.getPrice())
		 * .addValue("shipping", item.getShipping()).addValue("description",
		 * item.getDeccription());
		 */
		template.update(sql, param);

	}

	// カテゴリid、名前、ブランドでデータを取得する
	public ItemPage itemBysearch(SearchForm searchForm, List<String> categoryIdList, Integer page) {
		
		final StringBuilder sql = new StringBuilder();
		final StringBuilder sizeSql = new StringBuilder();
		final String name = searchForm.getName();
		final String brand = searchForm.getBrand();
		final Map<String, Object> paramMap = new HashMap<>();
		final Integer limit = 30;
		final Integer offset = limit * (page - 1);

		String selectSql = "select i.id, i.name, i.condition, i.category, i.brand, i.price, i.shipping, i.description,\r\n"
				+ "				c.id as category_id, c.parent, c.name as category_name, c.name_all\r\n"
				+ "				from items as i inner join category as c on i.category = c.id ";

		String nameSql = "where i.name like :name ";
		String brandSql = "and i.brand like :brand ";
		String categorySql = "and i.category in (:categoryId) ";
		String orderSql = "order by i.id limit :limit OFFSET :offset ";

		// name
		paramMap.put("name", "%" + name + "%");

		// brand
		if ("".equals(brand) || Objects.isNull(brand)) {
			brandSql = "";
		} else {
			paramMap.put("brand", "%" + brand + "%");
		}
		// categoryId
		if ("".equals(categoryIdList) || Objects.isNull(categoryIdList)) {
			categorySql = "";
		} else {

			List<Integer> idList = categoryIdList.stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());

			/* String catgoryIdi = String.join(",", categoryIdList); */

			/* Integer catgoryId = Integer.parseInt(catgoryIdi); */

			paramMap.put("categoryId", idList);

		}

		// limit
		paramMap.put("limit", limit);
		paramMap.put("offset", offset);

		// SQL文の生成
		sql.append(selectSql);
		sql.append(nameSql);
		sql.append(brandSql);
		sql.append(categorySql);
		sql.append(orderSql);

		List<ItemCategoryJoin> item = template.query(sql.toString(), paramMap, CI_ROW_MAPPER);
	

		//// 検索にマッチするitem数

		sizeSql.append(" SELECT COUNT(*) from items as i inner join category as c on i.category = c.id ");
		/* sizeSql.append(selectSql); */
		sizeSql.append(nameSql);
		sizeSql.append(brandSql);
		sizeSql.append(categorySql);
		final Integer size = template.queryForObject(sizeSql.toString(), paramMap, Integer.class);

		final ItemPage result = new ItemPage();
		result.setItemList(item);
		result.setSize(size);
		result.setPage(page);
		result.setTotalPage(size / limit + 1);
		return result;

	}

	// 親子のみ
	public List<Category> findParentChild(String fullName) {
		String sql = " select c.id as category_id, c.parent, c.name, c.name_all from category as c where c.name_all like :name ";
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("name", fullName + "%");
		List<Category> category = template.query(sql, param, C_ROW_MAPPER);
		return category;
	}

	// 子孫のみ
	public List<Category> findchildGrandchild(String fullName) {
		String sql = " select c.id as category_id, c.parent, c.name, c.name_all from category as c where c.name_all like :name ";
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + fullName);
		List<Category> category = template.query(sql, param, C_ROW_MAPPER);
		return category;
	}

	// 親孫のみ
	public List<Category> findparentGrandChild(String parent, String grandChild) {
		String sql = " select c.id as category_id, c.parent, c.name, c.name_all from category as c where c.name_all like :namep and c.name_all like :nameg ";
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("namep", parent + "%").addValue("nameg",
				"%" + grandChild);
		List<Category> category = template.query(sql, param, C_ROW_MAPPER);
		return category;
	}

	// 親のみ
	public List<Category> findparent(String parent) {
		String sql = " select c.id as category_id, c.parent, c.name, c.name_all from category as c where c.parent In (select c.id from category as c where c.parent In \r\n"
				+ "																(select c.id from category as c where c.id In (select c.id from category as c where \r\n"
				+ "																											   c.name = :name))) \r\n"
				+ "																											   order by c.id ";
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("name", parent);
		List<Category> category = template.query(sql, param, C_ROW_MAPPER);
		return category;
	}

	// 子のみ(できない)
	public List<Category> findchild1(String child) {
		String sql =" select c.id as category_id, c.parent, c.name, c.name_all from category as c where c.name = :name ";
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("name", child);
		List<Category> category = template.query(sql, param, C_ROW_MAPPER);
		return category;
	}

	// 孫のみ
	public List<Category> findGrandChild(String grandChild) {
		String sql = " select c.id as category_id, c.parent, c.name, c.name_all from category as c where c.name = :name ";
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("name", grandChild);
		List<Category> category = template.query(sql, param, C_ROW_MAPPER);
		return category;
	}
}
