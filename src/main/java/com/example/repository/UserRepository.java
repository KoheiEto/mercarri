package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public class UserRepository {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setMailAddress(rs.getString("mail_address"));
		user.setPassword(rs.getString("password"));
		/*
		 * user.setAuthority(rs.getInt("authority"));
		 * user.setUuid(rs.getString("uuid"));
		 * user.setRegisterDate(rs.getDate("register_date"));
		 */
		return user;
	};

	public void insert(User user) {
		String sql = "insert into users (mail_address,password)values(:mailAddress,:password)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", user.getMailAddress())
				.addValue("password", passwordEncoder.encode(user.getPassword()));
		template.update(sql, param);
	}

	// DBに登録されているメールアドレスかどうか判定
	public boolean existByEmail(String email) {

		String sql = "SELECT count(*) FROM users WHERE mail_address = :email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

		Integer count = template.queryForObject(sql, param, Integer.class);

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public User findByEmail(String email) {

		String sql = "SELECT id, mail_address, password  FROM users WHERE mail_address = :email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

		List<User> user = template.query(sql, param, USER_ROW_MAPPER);

		return user.get(0);
	}
	
	

}
