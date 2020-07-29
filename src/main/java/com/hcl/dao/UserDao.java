package com.hcl.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.hcl.model.Login;
import com.hcl.model.User;

public class UserDao extends NamedParameterJdbcDaoSupport{

	  @Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	    public User validateUser(Login login) {//this function is used for checking the username and password with the data base
	        String sql = "select * from User where name='" + login.getUsername() + "' and password='" + login.getPassword()
	        + "'";
	        List<User> users = jdbcTemplate.query(sql, new UserMapper());
	        return users.size() > 0 ? users.get(0) : null;
	        }
	      }
	      