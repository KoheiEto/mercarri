package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.domain.ItemCategoryJoin;

@Repository
public class ItemEditRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Category> C_ROW_MAPPER = (rs, i) -> {
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setParent(rs.getInt("parent"));
		category.setName(rs.getString("name"));
		category.setNameAll(rs.getString("name_all"));

		return category;
	};

	private static final RowMapper<Category> I_ROW_MAPPER = (rs, i) -> {
		Category category = new Category();
		category.setId(rs.getInt("id"));
		return category;
	};

	private static final RowMapper<String> S_ROW_MAPPER = (rs, i) -> {
		String string = new String();
		return string;
	};

	// カテゴリテーブルからnameが親のものを取得
	public List<Category> findByParentName() {
		String sql = "select * from category where parent is null order by id";
		List<Category> categories = template.query(sql, C_ROW_MAPPER);
		return categories;
	}

	// カテゴリテーブルからnameが子のものを取得
	public List<Category> findByChildName() {
		String sql = "select * from category where name_all is null and parent is not null order by id";
		List<Category> categories = template.query(sql, C_ROW_MAPPER);
		return categories;
	}

	// カテゴリテーブルからnameが孫のものを取得
	public List<Category> findByGrandChildName() {
		String sql = "select * from category where parent is  not null and name_all is  not null order by id";
		List<Category> categories = template.query(sql, C_ROW_MAPPER);
		return categories;
	}

	// 親カテゴリ名のidを取得
	public Category findIdByParentName(String parentName) {
		String sql = "select * from category where name = :parentName and parent isnull";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentName", parentName);
		Category category = template.queryForObject(sql, param, C_ROW_MAPPER);
		return category;

	}

	// 子カテゴリ名のidを取得
	public Category findIdByChildName(String childName) {
		String sql = "select * from category where name = :childName and parent notnull and name_all isnull";
		SqlParameterSource param = new MapSqlParameterSource().addValue("childName", childName);
		Category category = template.queryForObject(sql, param, C_ROW_MAPPER);
		return category;
	}

	// カテゴリテーブルからparentNameを指定してchildNameを取得
	public List<Category> findByChildNameByParentId(Integer parentId) {
		String sql = "select * from category where parent = :parentId order by id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentId", parentId);
		List<Category> childCategory = template.query(sql, param, C_ROW_MAPPER);
		return childCategory;
	}

	// カテゴリテーブルからchildtNameを指定してgrandChildNameを取得
	public List<Category> findGrandChildNameByChildId(Integer childId) {
		String sql = "select * from category where parent = :childId order by id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("childId", childId);
		List<Category> grandChildCategory = template.query(sql, param, C_ROW_MAPPER);
		return grandChildCategory;
	}

	public List<Category> findChildNameByParentId(Integer parentId) {
		String sql = "select * from category where parent = :parentId order by id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentId", parentId);
		List<Category> childCategory = template.query(sql, param, C_ROW_MAPPER);
		return childCategory;
	}

	// 孫カテゴリIDのレコードのparentId(子カテゴリID)を取得
	public Category findIdByParentId(Integer grandChildId) {
		String sql = "select * from category where id = :grandChildId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("grandChildId", grandChildId);
		Category category = template.queryForObject(sql, param, C_ROW_MAPPER);
		return category;
	}

	// 子カテゴリIDを親IDに持つレコードのリストを取得(孫カテゴリのリストを取得)
	public List<Category> findGrandChildList(Integer childId) {
		String sql = "select * from category where parent = :childId order by id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("childId", childId);
		List<Category> category = template.query(sql, param, C_ROW_MAPPER);
		return category;
	}

	public Category findCategoryIdByCategoryName(String CategoryName) {
		String sql = "select * from category where name_all = :CategoryName";
		SqlParameterSource param = new MapSqlParameterSource().addValue("CategoryName", CategoryName);
		Category category = template.queryForObject(sql, param, C_ROW_MAPPER);
		return category;
	}

	//
	public Category findParentByParentName(String parentName) {
		String sql = "select * from category where name = :parentName and parent isnull and name_all isnull";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentName", parentName);
		Category category = template.queryForObject(sql, param, C_ROW_MAPPER);
		return category;
	}

	///
	public List<Category> findChildList(Integer parentId) {
		String sql = "select * from category where parent = :parentId and name_all isnull";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentId", parentId);
		List<Category> category = template.query(sql, param, C_ROW_MAPPER);
		return category;
	}

	// 編集したitem情報を更新する
	public void editInsert(Item item) {
		String sql = "update items set name = :name, condition = :condition, category = :category, brand = :brand, price = :price, description = :description where id = :id ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", item.getId())
				.addValue("name", item.getName()).addValue("condition", item.getCondition())
				.addValue("category", item.getCategory()).addValue("brand", item.getBrand())
				.addValue("price", item.getPrice()).addValue("description", item.getDescription());
		template.update(sql, param);
	}

	// 子カテゴリ名前をもつレコードを取得
	public Category findChildCategory(String childName ,Integer id) {

		try {
			String sql = "select * from category where name = :childName and name_all isnull";
			SqlParameterSource param = new MapSqlParameterSource().addValue("childName", childName);
			Category category = template.queryForObject(sql, param, C_ROW_MAPPER);
			return category;
		} catch (IncorrectResultSizeDataAccessException e) {
			String sql = "select * from category where name = :childName and name_all isnull and parent = :id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("childName", childName);
			Category category = template.queryForObject(sql, param, C_ROW_MAPPER);
			return category;
		}

	}

	// そのidをparentとしてもつ孫カテゴリを取得
	public Category findGrandChildCategory(Integer id) {
		String sql = "select * from category where parent = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Category category = template.queryForObject(sql, param, C_ROW_MAPPER);
		return category;
	}

}
