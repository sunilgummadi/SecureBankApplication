package com.hcl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

import com.hcl.model.Login;
import com.hcl.model.TransactionLog;
import com.hcl.model.User;
import com.hcl.service.BankService;
import com.hcl.dao.TransMapper;
import com.hcl.dao.UserMapper;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

@Controller//controller annotation is used to specify that this particular java file acts as a controller
public class LoginController {
	
	@Autowired
	  BankService bankService;
	@Autowired
	  JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/")//request mapping annotation is used to map the http request with appropriate handler
	public String home() {
		System.out.println("home");
		return "Home";//specifying to print home page
	}

	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("login");//model and view is used to return both model and view in a single return statement
	    //model contains the data of the application and view represents the data in particular format
	    mav.addObject("login", new Login());

	    return mav;
	  }

	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public String loginProcess(HttpServletRequest request, HttpServletResponse response,
	      @ModelAttribute("login") Login login) {

	    User user = bankService.validateUser(login);//this function returns null if username and password doesn't match 
	    //else this will return User object
	    HttpSession session = request.getSession();
	    
		session.setAttribute("usr",user);//storing the user in session 
	    System.out.println(login.getUsername());
	    System.out.println(login.getPassword());
	    if (null != user) {//checking if user is null if not null entered user is authenticated user
	      return "redirect:/account";
	    } else {//else redirecting to login page to try login this will continues until he/she enters correct credentials
	      return "redirect:/login";
	      
	    }

	  }
	  @RequestMapping(value = "/transfering")
	  public String transferAmount(HttpServletRequest request, HttpServletResponse response) {
		  HttpSession session = request.getSession();
		  User user= (User) session.getAttribute("usr");//getting the user form session and typecasting with User 
		  if(user!=null) {
			  LocalDateTime myDateObj = LocalDateTime.now();//getting system date and time
			    System.out.println("Before formatting: " + myDateObj);
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");//formating into our required format
			    System.out.println("After formatting: " + myFormatObj);
			    String formattedDate = myDateObj.format(myFormatObj);
			    String balsql = "select * from User where name = '"+ user.getName()+"'";//sql query for getting the entire row from table User
			    
			    List<User> bal = jdbcTemplate.query(balsql, new UserMapper());//storing the output into list of user
			    System.out.println(bal.get(0).getBalance());
			    String sql = "update User set balance = "+(bal.get(0).getBalance()-1000.0)+" where name= '" +user.getName()+"'";//updating balance in table
			    int rows = jdbcTemplate.update(sql);
			    System.out.println(rows);
			    String date = "'"+formattedDate+"'";
			   
			    String insert = "insert into TransactionLog (debitedAmount,uid,updateDateTime) values ("+ 1000.0+","+bal.get(0).getUid()+","//inserting into transactionLog table 
+ date+")";
			    System.out.println(insert);
			    int status = jdbcTemplate.update(insert);
			   
			    
				return "redirect:/account";
		  }
		  else {
			  return "redirect:/login";
		  }
	  }
	  @RequestMapping("/logout")
	  public String logout(HttpServletRequest request)
	  {
		  HttpSession session =request.getSession();
		  
		  session.removeAttribute("usr");//removing the user from session so that user is no longer can access the portal until he login
		  session.invalidate();
		  return "redirect:/";//redirecting to home page
	  }
	  @RequestMapping(value= "/account")
	  public ModelAndView Account(HttpServletRequest request, HttpServletResponse response) {
		  HttpSession session = request.getSession();
		  User user= (User) session.getAttribute("usr");
		  if(user!=null) {
			  ModelAndView mav = new ModelAndView("Account"); 
			  String sql = "select * from User where name='" +user.getName()+ "'";
			  System.out.println(sql);
			  String last = "select * from TransactionLog order by id desc limit 5";//sql query for getting the last 5 transactions 
			  List<TransactionLog> trans = jdbcTemplate.query(last, new TransMapper());//storing the last 5 transactions into list
			  List<User> usser = jdbcTemplate.query(sql, new UserMapper());
			  System.out.println(usser.get(0).getAccountNo());
			  mav.addObject("translst",trans);
			  mav.addObject("balance",usser.get(0).getBalance());//adding the balance into model and view object to check whether the balance is Zero or not in jsp account page
			  mav.addObject("details",usser);
			  return mav;
		  }
		  else {
			  ModelAndView mav = new ModelAndView("Login"); 
			  return mav;
		  }
		  
		  
	  }

}
