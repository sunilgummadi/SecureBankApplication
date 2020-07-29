package com.hcl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hcl.model.TransactionLog;

public class TransMapper implements RowMapper<TransactionLog>{

	public TransactionLog mapRow(ResultSet rs, int rowNum) throws SQLException {
		TransactionLog trans = new TransactionLog();
		trans.setDebitedAmount(rs.getDouble("debitedAmount"));
		trans.setId(rs.getInt("id"));
		trans.setUpdateDateTime(rs.getString("updateDateTime"));
		trans.setUid(rs.getLong("uid"));
		return trans;
	}

}
