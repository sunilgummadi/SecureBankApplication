package com.hcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.dao.UserDao;
import com.hcl.model.Login;
import com.hcl.model.User;


@Service
@Transactional
public class BankService {

	@Autowired
	UserDao usr;
	public User validateUser(Login login)
	{
		   return usr.validateUser(login); 
	}
}
