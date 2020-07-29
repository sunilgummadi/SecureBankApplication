package com.hcl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hcl.model.User;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int arg1) throws SQLException {
      User user = new User();
      user.setName(rs.getString("name"));
      user.setPassword(rs.getString("password"));
      user.setAccountNo(rs.getLong("accountNo"));
      user.setBalance(rs.getDouble("balance"));
      user.setCity(rs.getString("city"));
      user.setState(rs.getString("state"));
      user.setCountry(rs.getString("country"));
      user.setPhoneno(rs.getLong("phoneno"));
      user.setPincode(rs.getInt("pincode"));
      return user;
    }
}
